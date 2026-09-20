package io.truthencode.dal.general;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.logging.Log;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.CompositeException;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.truthencode.dal.general.JSONSupport.UPDATE_KEYS_HEADER;
import static jakarta.ws.rs.core.Response.Status.*;

@Path("Feats")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FeatResource {

    @Context
    HttpServerRequest request;

    @Context
    UriInfo uriInfo;

    private static final Logger LOGGER = Logger.getLogger(FeatResource.class.getName());

    // Validation
    @Inject
    Validator validator;

    @Path("/manual-validation")
    @POST
    public ValidationResult tryMeManualValidation(Feat feat) {
        Set<ConstraintViolation<Feat>> violations = validator.validate(feat);
        if (violations.isEmpty()) {
            return new ValidationResult("Feat is valid! It was validated by manual validation.");
        } else {
            return new ValidationResult(violations);
        }
    }

    @Path("/end-point-method-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ValidationResult tryMeEndPointMethodValidation(@Valid Feat feat) {
        return new ValidationResult("Feat is valid! It was validated by end point method validation.");
    }

    @Inject
    FeatService featService;

    @Path("/service-method-validation")
    @POST
    public ValidationResult tryMeServiceMethodValidation(Feat feat) {
        try {
            featService.validateFeat(feat);
            return new ValidationResult("Feat is valid! It was validated by service method validation.");
        } catch (ConstraintViolationException e) {
            return new ValidationResult(e.getConstraintViolations());
        }
    }

    @GET
    public Uni<List<Feat>> get() {
        return Feat.listAll(Sort.by("name"));
    }

    @GET
    @Path("{id}")
    public Uni<Feat> getSingle(Long id) {
        return Feat.findById(id);
    }

    @POST
    public Uni<Response> create(Feat feat) {
        if (feat == null || feat.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        return Panache.withTransaction(feat::persist)
            .replaceWith(Response.ok(feat).status(CREATED)::build);
    }

    @KeyExtracting
    @PUT
    @Path("{id}")
    @Consumes(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Uni<Response> updateFromRaw(Long id, String body, @HeaderParam(value = UPDATE_KEYS_HEADER) String header) {
//        Response.ResponseBuilder builder = null;
        Log.warn("@KeyExtracting updateFromRaw: " + body);
        Log.warn("@KeyExtracting updateFromRaw: header " + header);
        return Panache
            .withTransaction(() -> Feat.<Feat>findById(id)
                .onItem().ifNotNull().invoke(entity -> {
                    try {
                        var kMap = JSONSupport.extractKeys(body, header);
                        var keys = kMap.keys();
                        var rootNode = kMap.node();

                        if (keys.isEmpty()) {
                            throw new WebApplicationException("Field keys were not set on request and could not determine fields to update. Please specify update fields by Header, Query parameters", 422);
                        }
                        Log.warn("Attempting to update Feat with id: " + id + " using requested keys: " + keys);
                        keys.stream()
                            .map(String::toLowerCase)
                            .map(String::trim)
                            .forEach(fieldKey -> {
                                Log.warn("Updating field [" + fieldKey + "]");
                                switch (fieldKey) {
                                    case "id":
                                        Log.warn("ID field can not be changed.");
                                        break;
                                    case "name":
                                        Log.info("Matched field [name]");
                                        Log.warn("Mapping name: " + rootNode.findValues(fieldKey).stream().findFirst().get().asText());
                                        entity.name = rootNode.findValues(fieldKey).stream().findFirst().get().asText();
                                        break;
                                    case "description":
                                        Log.info("Matched field [description]");
                                        entity.description = rootNode.findValues(fieldKey).stream().findFirst().map(JsonNode::asText).orElse(null);
                                        break;
                                    case "usages":
                                        Log.info("Matched field [usages]");
                                        if (rootNode.findValues(fieldKey).stream().findFirst().isPresent()) {
                                            entity.usages = new HashSet<>();
                                            rootNode.findValue(fieldKey).forEach(j -> {
                                                Log.warn("Adding usage -> " + j);
                                                entity.usages.add(Usage.valueOf(j.asText()));
                                            });
                                        }
                                        break;
                                    default:
                                        Log.warn("Field [" + fieldKey + "] is not mapped or does not exist. Corresponding value will not be updated.");
                                }
                            });
                        featService.validateFeat(entity);
                    } catch (IOException e) {
                        throw new RuntimeException("Error processing JSON", e);
                    }
                })
            )
            .onItem().ifNotNull().transform(entity -> Response.ok(entity).status(OK).build())
            .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);


    }


    /**
     * Update an existing Feat.
     * Currently, this will only add / change existing data, but not remove data.
     *
     * @param id   The id of the Feat to update.
     * @param feat The Feat to update.
     * @return A Response with the updated Feat.
     */
    @PUT
    @Path("raw/{id}")
    public Uni<Response> updateFromRaw(
        Long id,
        Feat feat,
        @HeaderParam(value = UPDATE_KEYS_HEADER) String fieldKeys,
//        @FormParam(value = UPDATE_KEYS_HEADER) String formKeys,
        @QueryParam(value = JSONSupport.UPDATE_KEYS_HEADER) String queryKeys) {
        if (feat == null || feat.name == null) {
            throw new WebApplicationException("Feat name was not set on request.", 422);
        }

        Log.warn("Attempting to update raw/ Feat with id: " + id);
        return Panache
            .withTransaction(() -> PanacheEntityBase.<Feat>findById(id)
                .onItem().ifNotNull().invoke(entity -> {
                    var r = request.getHeader(UPDATE_KEYS_HEADER);
                    // String r = "description,name";
                    Log.warn("header from httpserverrequest: " + r);
                    var keys = JSONSupport.getKeys(r, fieldKeys, queryKeys);
                    Log.warn("Attempting to update Feat with id: " + id + "using requested keys: " + keys);
                    if (keys.isEmpty()) {
                        throw new WebApplicationException("Field keys were not set on request.  Please specify update fields by Header, Query parameters", 422);
                    }
                    Log.warn("Attempting to update Feat with id: " + id + "using requested keys: " + keys);
                    keys.stream()
                        .map(String::toLowerCase)
                        .map(String::trim)
                        .forEach(fieldKey -> {
                            Log.warn("Updating field [" + fieldKey + "]");
                            switch (fieldKey) {
                                case "id":
                                    // Log.warn("Matched field [id]");
                                    // We don't want to update the id. Should either error or ignore.
                                    Log.warn("ID field can not be changed.");
                                    break;
                                case "name":
                                    Log.info("Matched field [name]");
                                    entity.name = feat.name;
                                    break;
                                case "description":
                                    Log.info("Matched field [description]");
                                    entity.description = feat.description;
                                    break;
                                case "usages":
                                    Log.info("Matched field [usages]");
                                    entity.usages = feat.usages;
                                    break;
                                default:
                                    Log.warn("Field [" + fieldKey + "] is not mapped or does not exist. Value will not be updated.");

                            }

                        });

                        /* Need to go field by field to check for change on required.
                         How do we know if nullable / optional fields are set?
                         If not supplied in JSON, they are defaulted to null in the Feat object.
                         If supplied in JSON, but set to null, then it is set, and we want to update.
                         However, both options are identical at this point in the code as the JSON has been automatically mapped to the Feat object.
                         So, we then need to generate all missing fields to existing values prior to calling this method.
                         */
                })
            )
            .onItem().ifNotNull().transform(entity -> Response.ok(entity).status(OK).build())
            .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(Long id) {
        return Panache.withTransaction(() -> Feat.deleteById(id))
            .map(deleted -> deleted
                ? Response.ok().status(NO_CONTENT).build()
                : Response.ok().status(NOT_FOUND).build());
    }

    /**
     * Create an HTTP response from an exception.
     * <p>
     * Response Example:
     *
     * <pre>
     * HTTP/1.1 422 Unprocessable Entity
     * Content-Length: 111
     * Content-Type: application/json
     *
     * {
     *     "code": 422,
     *     "error": "Feat name was not set on request.",
     *     "exceptionType": "jakarta.ws.rs.WebApplicationException"
     * }
     * </pre>
     */
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {
        // should move to common utility class.
        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception exception) {
            LOGGER.error("Failed to handle request", exception);

            Throwable throwable = exception;

            int code = 500;
            if (throwable instanceof WebApplicationException) {
                code = ((WebApplicationException) exception).getResponse().getStatus();
            }

            // This is a Mutiny exception, and it happens, for example, when we try to insert a new
            // Feat but the name is already in the database
            if (throwable instanceof CompositeException) {
                throwable = ((CompositeException) throwable).getCause();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", throwable.getClass().getName());
            exceptionJson.put("code", code);

            if (exception.getMessage() != null) {
                exceptionJson.put("error", throwable.getMessage());
            }

            return Response.status(code)
                .entity(exceptionJson)
                .build();
        }

    }
}
