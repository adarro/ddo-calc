package io.truthencode.ddo.codex.security;

import io.quarkus.security.UnauthorizedException;
import jakarta.annotation.Priority;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;


@Provider//register it as jaxrs provider
@Priority(1)//lower number-higher priority
//exception mappers are selected on best match for the exception class
//so make sure you find the particular exception you want to map
// Credit: https://stackoverflow.com/a/69978804/400729
public class MyUnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
    @Context//you can inject JAXRS contextual resources here
    UriInfo crc;


    private static final Logger logger = Logger.getLogger(MyUnauthorizedExceptionMapper.class);
    @Override
    public Response toResponse(UnauthorizedException exception) {
        logger.warn("User not authorized to access: " + crc.getRequestUri());
        return Response //seeOther = 303 redirect
            .seeOther(UriBuilder.fromUri("/Login/login")
                //common pattern is to pass the original URL as param,
                //so that after successful login, you redirect user back where he wanted
                .queryParam("returnUrl", crc.getRequestUri())
                .build())//build the URL where you want to redirect
            .entity("Not authorized")//entity is not required
            .build();
    }
}