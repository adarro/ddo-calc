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
import io.truthencode.ddo.dal.entity.Feat;
import io.truthencode.ddo.dal.repositories.FeatRepository;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.truthencode.dal.general.JSONSupport.UPDATE_KEYS_HEADER;
import static jakarta.ws.rs.core.Response.Status.*;

/**
 * REST Resource for managing Feat entities, providing CRUD operations and validation methods.
 * <p>
 * This class serves as a RESTful endpoint for Feat-related operations, supporting:
 * - Retrieving all Feats
 * - Retrieving a single Feat by ID
 * - Creating new Feats
 * - Updating existing Feats
 * - Deleting Feats
 * - Multiple validation approaches for Feat objects
 * <p>
 * Supports various validation strategies including manual, end-point method,
 * and service method validations. Utilizes Quarkus reactive programming
 * with Hibernate and Panache for data persistence.
 *
 * @see FeatRepository
 * @see FeatService
 */
@Path("Feats")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class FeatResource {
    /**
     * Default constructor for FeatResource.
     * <p>
     * Provides a no-argument constructor for creating instances of FeatResource
     * without any specific initialization requirements.
     */
    public FeatResource() {
        // Default constructor
    }

    @Context
    HttpServerRequest request;

    @Context
    UriInfo uriInfo;

    @Inject
    FeatRepository repository;

    private static final Logger LOGGER = Logger.getLogger(FeatResource.class.getName());

    // Validation
    @Inject
    Validator validator;

    /**
     * Validates a Feat using manual validation through the validator.
     * <p>
     * This method manually validates a Feat object using the injected Validator.
     * If no constraint violations are found, it returns a positive validation result.
     * If constraint violations exist, it returns a validation result containing the specific violations.
     *
     * @param feat The Feat object to be manually validated
     * @return A ValidationResult indicating whether the Feat is valid or detailing any constraint violations
     */
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

    /**
     * Validates a Feat using end-point method validation.
     * <p>
     * This method leverages the @Valid annotation to perform automatic validation
     * of the Feat object at the endpoint level. If the Feat passes validation,
     * it returns a positive validation result indicating successful validation.
     *
     * @param feat The Feat object to be validated using end-point method validation
     * @return A ValidationResult confirming the Feat's validity
     */
    @Path("/end-point-method-validation")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ValidationResult tryMeEndPointMethodValidation(@Valid Feat feat) {
        return new ValidationResult("Feat is valid! It was validated by end point method validation.");
    }

    @Inject
    FeatService featService;

    /**
     * Validates a Feat using the service method validation approach.
     * <p>
     * This method attempts to validate a Feat through the featService's validateFeat method.
     * If validation is successful, it returns a positive validation result.
     * If a ConstraintViolationException is thrown during validation, it returns a validation result
     * containing the specific constraint violations.
     *
     * @param feat The Feat object to be validated
     * @return A ValidationResult indicating whether the Feat is valid or detailing any constraint violations
     */
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

    /**
     * Retrieves all Feats from the database.
     *
     * @return A Uni representing the asynchronous retrieval of all Feats
     */
    @GET
    public Uni<List<Feat>> get() {
        return repository.listAll(Sort.by("name"));
    }

    /**
     * Retrieves a single Feat entity by its unique identifier.
     *
     * @param id The unique identifier of the Feat to be retrieved
     * @return A Uni representing the asynchronous retrieval of the Feat entity
     */
    @GET
    @Path("{id}")
    public Uni<Feat> getSingle(Long id) {
        return repository.findById(id);
    }

    /**
     * Creates a new Feat entity in the database.
     * <p>
     * This method validates that the incoming Feat does not already have an ID set,
     * and then persists the new Feat entity within a transaction. Upon successful
     * creation, it returns a response with the created Feat and a CREATED status.
     *
     * @param feat The Feat entity to be created
     * @return A Uni representing the asynchronous creation of the Feat with a response
     * @throws WebApplicationException if the Feat already has an ID set
     */
    @POST
    public Uni<Response> create(Feat feat) {
        if (feat == null || feat.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        return Panache.withTransaction(() -> repository.persist(feat))
            .replaceWith(Response.ok(feat).status(CREATED)::build);
    }

    /**
     * Updates a Feat entity by processing a JSON payload with specified update keys.
     * <p>
     * This method allows partial updates to a Feat by extracting specific fields from the request body
     * and header. It supports updating fields like name, description, and usages while performing
     * validation through the featService.
     *
     * @param id     The identifier of the Feat to be updated
     * @param body   The JSON payload containing the update data
     * @param header A header specifying which fields should be updated
     * @return A Uni representing the HTTP response with the updated Feat or appropriate status
     * @throws WebApplicationException if no update keys are found or JSON processing fails
     */
    @KeyExtracting
    @PUT
    @Path("{id}")
    @Consumes(value = {MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Uni<Response> updateFromRaw(Long id, String body, @HeaderParam(value = UPDATE_KEYS_HEADER) String header) {
//        Response.ResponseBuilder builder = null;
        Log.warn("@KeyExtracting updateFromRaw: " + body);
        Log.warn("@KeyExtracting updateFromRaw: header " + header);
        return Panache
            .withTransaction(() -> repository.findById(id)
                .onItem().ifNotNull().invoke(entity -> {
                    try {
                        var kMap = JSONSupport.extractKeys(body, header);
                        var keys = kMap.keys();
                        var rootNode = kMap.node();

                        if (keys.isEmpty()) {
                            throw new WebApplicationException("Field keys were not set on request and could not determine fields to update. Please specify update fields by Header, Query parameters", 422);
                        }
                        Log.warnf("Attempting to update Feat with id: {} using requested keys: ()", id, keys);
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
                                        entity.setName(rootNode.findValues(fieldKey).stream().findFirst().get().asText());
                                        break;
                                    case "description":
                                        Log.info("Matched field [description]");
                                        entity.setDescription(rootNode.findValues(fieldKey).stream().findFirst().map(JsonNode::asText).orElse(null));
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
     * @param fieldKeys The header field keys to update.
     * @param queryKeys The keys from the query string to update.
     * @return A Response with the updated Feat.
     */
    @PUT
    @Path("raw/{id}")
    public Uni<Response> updateFromRaw(
        Long id,
        Feat feat,
        @HeaderParam(value = UPDATE_KEYS_HEADER) String fieldKeys,
        @QueryParam(value = JSONSupport.UPDATE_KEYS_HEADER) String queryKeys) {
        if (feat == null || feat.getName() == null) {
            throw new WebApplicationException("Feat name was not set on request.", 422);
        }

        Log.warn("Attempting to update raw/ Feat with id: " + id);
        return Panache
            .withTransaction(() -> repository.findById(id)
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
                                    entity.setName(feat.getName());
                                    break;
                                case "description":
                                    Log.info("Matched field [description]");
                                    entity.setDescription(feat.getDescription());
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

    /**
     * Deletes a Feat entity by its unique identifier.
     *
     * @param id The unique identifier of the Feat to be deleted
     * @return A Uni representing the asynchronous deletion operation, which returns a Response
     *         indicating the result of the deletion (NO_CONTENT if successful, NOT_FOUND if the entity does not exist)
     */
    @DELETE
    @Path("{id}")
    public Uni<Response> delete(Long id) {
        return Panache.withTransaction(() -> repository.deleteById(id))
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

        /**
         * Converts an exception to an HTTP response with appropriate error details.
         * <p>
         * This method handles different types of exceptions, extracting status codes and error messages
         * to create a standardized JSON error response. It supports:
         * - WebApplicationExceptions with specific HTTP status codes
         * - CompositeExceptions by extracting the underlying cause
         * - Logging the full exception details
         *
         * @param exception The exception to be converted into a response
         * @return A JAX-RS Response containing error details in JSON format
         */
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
            if (throwable instanceof CompositeException exception1) {
                throwable = exception1.getCause();
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
