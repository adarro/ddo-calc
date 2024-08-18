package io.truthencode.dal.general;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import static io.truthencode.dal.general.JSONSupport.UPDATE_KEYS_HEADER;


class NamedFieldUpdateFilters {
//        @ServerRequestFilter(preMatching = true)
//        public Uni<Void> preMatchingFilter(ContainerRequestContext ctx) {
//            Log.warn("preMatchingFilter: " + ctx.getMethod() + " " + ctx.getUriInfo().getPath());
//            if (ctx.getMethod().equals(HttpMethod.PUT)
//                    && ctx.getUriInfo().getPath().contains("/Feats")
//                    && ctx.hasEntity()) {
//
//                Log.warn("preMatchingFilter: " + ctx.getMethod() + " " + ctx.getUriInfo().getPath() + " " + ctx.getHeaders());
//            }
//
//            return Uni.createFrom().voidItem();
//        }//    @ServerRequestFilter(preMatching = true)


    @ServerRequestFilter(preMatching = true)
    public Uni<Void> matchingFilter(ContainerRequestContext ctx) {
        Log.warn("Default MatchingFilter: " + ctx.getMethod() + " " + ctx.getUriInfo().getPath());
        if (ctx.getMethod().equals(HttpMethod.PUT)
            && ctx.getUriInfo().getPath().contains("/Feats")
            && ctx.hasEntity()) {
            Log.warn("Default PUT MatchingFilter: " + ctx.getMethod() + " " + ctx.getUriInfo().getPath() + " " + ctx.getHeaders());
            var headers = ctx.getHeaders().get(UPDATE_KEYS_HEADER);
            if (headers != null) {
                Log.warn("preMatchingFilter: appending update header values onto query string");
//                ctx.getUriInfo().getQueryParameters().addAll(UPDATE_KEYS_HEADER,headers);

            }


        }

        return Uni.createFrom().voidItem();
    }//    @ServerRequestFilter(preMatching = true)
//    public Uni<Void> preMatchingFilter(ContainerRequestContext ctx) {
//        return Uni.createFrom().nullItem().onItem().invoke(() -> {
//            Log.warn("preMatchingFilter: " + ctx.getMethod() + " " + ctx.getUriInfo().getPath());
//        }).onItem().invoke(() -> {
//            if (ctx.getMethod().equals(HttpMethod.PUT)
//                && ctx.getUriInfo().getPath().contains("/Feats")
//                && ctx.hasEntity()) {
//
//
//                String body = new BufferedReader(new InputStreamReader(ctx.getEntityStream())).lines()
//                    .collect(Collectors.joining("\n"));
////                    String body = new String(ctx.getEntityStream().readAllBytes(), StandardCharsets.UTF_8);
//                Log.warn("Feat put request received.  body is " + body);
//
//                // Add supplied JSON fields to header.
//                Log.warn("Feat put request received.  We should do something cool here.");
//            }
//        }).replaceWithVoid();
//    }

//    @Inject
//    Vertx vertx;
//
//    void foo() {
//        vertx.executeBlocking(Uni.createFrom().item("elkjs") {
//            authService.getIdToken("someHash")
//        })
//    }
//
//    void blah(ContainerRequestContext ctx) {
//        vertx.executeBlocking(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                byte[] bytes = ctx.getEntityStream().readAllBytes();
//                String json = new String(bytes, StandardCharsets.UTF_8);
//                return json;
//            }
//        });
//
//    }
//
//
//    @ServerRequestFilter(preMatching = true)
//    public Uni<Void> preMatchingFilter(ContainerRequestContext ctx) {
//        if (ctx.getMethod().equals(HttpMethod.PUT)
//            && ctx.getUriInfo().getPath().contains("/Feats")
//            && ctx.hasEntity()) {
//
//            return vertx.executeBlocking(Uni.createFrom().item(ctx.getEntityStream())
//                .onItem().transformToUni(stream -> {
//                    try {
//                        byte[] bytes = stream.readAllBytes();
//                        return Uni.createFrom().item(bytes);
//                    } catch (IOException e) {
//                        return Uni.createFrom().failure(e);
//                    }
//                })
//                .onItem().invoke(bytes -> {
//                        String json = new String(bytes, StandardCharsets.UTF_8);
//                    Log.warn("preMatchingFilter ++ " + json);
//                })
//                .onItem().invoke(() -> {
//                    if (ctx.getMethod().equals(HttpMethod.PUT)
//                        && ctx.getUriInfo().getPath().contains("/Feats")
//                        && ctx.hasEntity()) {
//                        // Add your logic here
//                    }
//                })
//                .replaceWithVoid());
//        }
//        return Uni.createFrom().voidItem();
//    }



//    @ServerRequestFilter
//    public Optional<RestResponse<Void>> getFilter(ContainerRequestContext ctx) {
//        // only allow GET methods for now
//        if (!ctx.getMethod().equals(HttpMethod.GET)) {
//            return Optional.of(RestResponse.status(Response.Status.METHOD_NOT_ALLOWED));
//        }
//        return Optional.empty();
//    }
}
