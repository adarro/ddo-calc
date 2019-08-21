package ro.trusted.web;

import com.google.common.base.Throwables;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.RoutingContext;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.olingo.commons.api.ex.ODataNotSupportedException;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpMethod;
import org.apache.olingo.server.api.*;
import org.apache.olingo.server.api.debug.DebugSupport;
import org.apache.olingo.server.api.etag.CustomETagSupport;
import org.apache.olingo.server.api.etag.PreconditionException;
import org.apache.olingo.server.api.processor.Processor;
import org.apache.olingo.server.api.serializer.CustomContentTypeSupport;
import org.apache.olingo.server.core.ODataExceptionHelper;
import org.apache.olingo.server.core.ODataHandlerException;
import org.apache.olingo.server.core.ODataHandlerImpl;
import org.apache.olingo.server.core.debug.ServerCoreDebugger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//import org.apache.olingo.server.core.ODataExceptionHelper;
//import org.apache.olingo.server.core.ODataHandlerException;
//import org.apache.olingo.server.core.ODataHandlerImpl;
//import org.apache.olingo.server.core.debug.ServerCoreDebugger;

@Data
public class VertxOdataHandlerImpl implements Handler<RoutingContext> {

    private final ODataHandlerImpl handler;
     private final ServerCoreDebugger debugger;

    public VertxOdataHandlerImpl(final OData odata, final ServiceMetadata serviceMetadata) {
        debugger = new ServerCoreDebugger(odata);
       // new ODataHttpHandler(odata.createHandler(serviceMetadata))
        handler = new ODataHandlerImpl(odata, serviceMetadata, debugger);
    }

    static void convertToHttp(final RoutingContext ctx, final ODataResponse odResponse) {
        ctx.response().setStatusCode(odResponse.getStatusCode());

        copyHeadersFromOdResponse(ctx, odResponse);

        if (odResponse.getContent() != null) {
            sendContent(ctx, odResponse);
        } else if (odResponse.getODataContent() != null) {
            sendODataContent(ctx, odResponse);
        } else {
            ctx.response().end();
        }
    }

    private static void sendODataContent(RoutingContext ctx, ODataResponse odResponse) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        odResponse.getODataContent().write(bos);

        ctx.response().end(Buffer.buffer(bos.toByteArray()));
    }

    private static void sendContent(RoutingContext ctx, ODataResponse odResponse) {
        try {
            ctx.response()
                .end(Buffer.buffer(IOUtils.toByteArray(odResponse.getContent())));
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }

    private static void copyHeadersFromOdResponse(RoutingContext ctx, ODataResponse odResponse) {
        for (Map.Entry<String, List<String>> entry : odResponse.getAllHeaders().entrySet()) {
            for (String headerValue : entry.getValue()) {
                ctx.response().putHeader(entry.getKey(), headerValue);
            }
        }
    }

    static HttpMethod extractMethod(final HttpServerRequest request) throws ODataLibraryException {
        final HttpMethod httpRequestMethod;
        String rawMethod = request.rawMethod();
        try {
            httpRequestMethod = HttpMethod.valueOf(rawMethod);
        } catch (IllegalArgumentException e) {
            throw new ODataNotSupportedException("HTTP method not allowed" + rawMethod, e);
        }
        try {
            if (httpRequestMethod == HttpMethod.POST) {
                String xHttpMethod = request.getHeader(HttpHeader.X_HTTP_METHOD);
                String xHttpMethodOverride = request.getHeader(HttpHeader.X_HTTP_METHOD_OVERRIDE);

                if (xHttpMethod == null && xHttpMethodOverride == null) {
                    return httpRequestMethod;
                } else if (xHttpMethod == null) {
                    return HttpMethod.valueOf(xHttpMethodOverride);
                } else if (xHttpMethodOverride == null) {
                    return HttpMethod.valueOf(xHttpMethod);
                } else {
                    if (!xHttpMethod.equalsIgnoreCase(xHttpMethodOverride)) {
                        throw new PreconditionException("Ambiguous X-HTTP-Methods",
                        PreconditionException.MessageKeys.FAILED, xHttpMethod, xHttpMethodOverride);
                    }
                    return HttpMethod.valueOf(xHttpMethod);
                }
            } else {
                return httpRequestMethod;
            }
        } catch (IllegalArgumentException e) {
            throw new PreconditionException("Invalid HTTP method" + request.rawMethod(), e,
                    PreconditionException.MessageKeys.FAILED, request.rawMethod());
        }
    }

    static void fillUriInformation(final ODataRequest odRequest,
                                   final HttpServerRequest request,
                                   final String mountPoint) {
        String rawRequestUri = request.uri();
        //TODO: this might not be true
        String rawODataPath = StringUtils.removeStart(rawRequestUri, mountPoint);

        String rawServiceResolutionUri = mountPoint;

        String rawBaseUri = rawRequestUri.substring(0, rawRequestUri.length() - rawODataPath.length());

        odRequest.setRawQueryPath(request.query());
        odRequest.setRawRequestUri(rawRequestUri + (request.query() == null ? "" : "?" + request.query()));
        odRequest.setRawODataPath(rawODataPath);
        odRequest.setRawBaseUri(rawBaseUri);
        odRequest.setRawServiceResolutionUri(rawServiceResolutionUri);
    }

    static void copyHeadersToOdRequest(ODataRequest odRequest, final HttpServerRequest req) {
        for (final String header : req.headers().names()) {
            odRequest.addHeader(header, req.headers().getAll(header));
        }
    }

    public ODataResponse process(ODataRequest request) {
        return handler.process(request);
    }

    private Map<String, String> createEnvironmentVariablesMap(final RoutingContext ctx) {
        Map<String, String> environment = new LinkedHashMap<String, String>();
        environment.put("authType", "authType");
        environment.put("localAddr", ctx.request().localAddress().host());
        environment.put("localName", ctx.request().localAddress().host());
        environment.put("localPort", getIntAsString(ctx.request().localAddress().port()));
        environment.put("pathInfo", ctx.request().path());
        environment.put("pathTranslated", ctx.normalisedPath());
        environment.put("remoteAddr", ctx.request().remoteAddress().host());
        environment.put("remoteHost", ctx.request().host());
        environment.put("remotePort", Integer.toString(ctx.request().remoteAddress().port()));
        environment.put("remoteUser", "remoteUser");
        environment.put("scheme", ctx.request().scheme());
        environment.put("serverName", ctx.request().host());
        environment.put("serverPort", getIntAsString(ctx.request().localAddress().port()));
        //TODO: this might be important
        environment.put("servletPath", ctx.mountPoint());
        return environment;
    }

    private String getIntAsString(final int number) {
        return number == 0 ? "unknown" : Integer.toString(number);
    }

    private ODataResponse handleException(final ODataRequest odRequest, final Exception e) {
        ODataResponse resp = new ODataResponse();
        ODataServerError serverError;
        if (e instanceof ODataHandlerException) {
            serverError = ODataExceptionHelper.createServerErrorObject((ODataHandlerException) e, null);
        } else if (e instanceof ODataLibraryException) {
            serverError = ODataExceptionHelper.createServerErrorObject((ODataLibraryException) e, null);
        } else {
            serverError = ODataExceptionHelper.createServerErrorObject(e);
        }
        handler.handleException(odRequest, resp, serverError, e);
        return resp;
    }

    private ODataRequest fillODataRequest(final ODataRequest odRequest, final RoutingContext ctx)
        throws ODataLibraryException {
//        final int requestHandle = debugger.startRuntimeMeasurement("ODataHttpHandlerImpl", "fillODataRequest");
        try {
            odRequest.setBody(new ByteArrayInputStream(ctx.getBody().getBytes()));
            odRequest.setProtocol(ctx.request().scheme());
            odRequest.setMethod(extractMethod(ctx.request()));
//            int innerHandle = debugger.startRuntimeMeasurement("ODataHttpHandlerImpl", "copyHeaders");
            copyHeadersToOdRequest(odRequest, ctx.request());
//            debugger.stopRuntimeMeasurement(innerHandle);
//            innerHandle = debugger.startRuntimeMeasurement("ODataHttpHandlerImpl", "fillUriInformation");
            fillUriInformation(odRequest, ctx.request(), ctx.mountPoint());
//            debugger.stopRuntimeMeasurement(innerHandle);

            return odRequest;
//        } catch (final IOException e) {
//            throw new DeserializerException("An I/O exception occurred.", e,
//                DeserializerException.MessageKeys.IO_EXCEPTION);
        } finally {
//            debugger.stopRuntimeMeasurement(requestHandle);
        }
    }

    public void register(final Processor processor) {
        handler.register(processor);
    }

    public void register(OlingoExtension extension) {
        handler.register(extension);
    }

    public void register(final CustomContentTypeSupport customContentTypeSupport) {
        handler.register(customContentTypeSupport);
    }

    public void register(final CustomETagSupport customConcurrencyControlSupport) {
        handler.register(customConcurrencyControlSupport);
    }

    public void register(final DebugSupport debugSupport) {
        // TODO: need to find something to attach this to as ServerCoreDebugger was a beta thing.
        //debugger.setDebugSupportProcessor(debugSupport);
    }

    @Override
    public void handle(RoutingContext ctx) {
        ODataRequest odRequest = new ODataRequest();
        Exception exception = null;
        ODataResponse odResponse;
//        debugger.resolveDebugMode(ctx.request());

//        final int processMethodHandle = debugger.startRuntimeMeasurement("ODataHttpHandlerImpl", "process");
        try {
            fillODataRequest(odRequest, ctx);

            odResponse = process(odRequest);
            // ALL future methods after process must not throw exceptions!
        } catch (Exception e) {
            exception = e;
            odResponse = handleException(odRequest, e);
        }
//        debugger.stopRuntimeMeasurement(processMethodHandle);

        if (debugger.isDebugMode()) {
            Map<String, String> serverEnvironmentVariables = createEnvironmentVariablesMap(ctx);
            if (exception == null) {
                // This is to ensure that we have access to the thrown OData Exception
                exception = handler.getLastThrownException();
            }
            odResponse =
                debugger.createDebugResponse(odRequest, odResponse, exception, handler.getUriInfo(),
                    serverEnvironmentVariables);
        }

        convertToHttp(ctx, odResponse);
    }

}
