package io.truthencode.dal.general;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.truthencode.dal.general.JSONSupport.UPDATE_KEYS_HEADER;

@Provider
public class RequestServerReaderInterceptor implements ReaderInterceptor {

    /**
     * Extracts explicitly set JSON fields from the request body and adds the keys to the X-UPDATE-KEYS header.
     * This is used to determine which fields are being updated.
     *
     * @param context invocation context.
     * @return the invocation context with the X-UPDATE-KEYS header added if any
     * @throws IOException             when things go boom.
     * @throws WebApplicationException when things go web-boom.
     */
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context)
        throws IOException, WebApplicationException {
        Log.warn("aroundReadFrom Intercepted request");
        Log.warn("checking method " + context.getHeaders());

        MultivaluedMap<String, String> inboundHeaders = context.getHeaders();
        if (!inboundHeaders.containsKey(UPDATE_KEYS_HEADER)) {
            Log.warn("No " + UPDATE_KEYS_HEADER + " header found, attempting to infer keys");
            InputStream is = context.getInputStream();
            String body = new BufferedReader(new InputStreamReader(is)).lines()
                .collect(Collectors.joining("\n"));
            // JSONify the body and extract keys to X-UPDATE-KEYS header
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = mapper.getFactory();
            JsonParser jp = factory.createParser(body);
            JsonNode actualObj = mapper.readTree(jp);
            List<String> headers = new ArrayList<>();
            actualObj.fieldNames().forEachRemaining(headers::add);
            String headersString = String.join(",", headers);
            Log.warn("Adding header " + UPDATE_KEYS_HEADER + ":" + headersString);
            context.getHeaders().add(UPDATE_KEYS_HEADER, headersString);


            context.setInputStream(new ByteArrayInputStream(
                (body).getBytes()));

        }
        Log.warn("before proceed, headers are " + context.getHeaders());

        return context.proceed();
    }
}
