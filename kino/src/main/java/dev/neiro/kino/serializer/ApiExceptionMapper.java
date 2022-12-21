package dev.neiro.kino.serializer;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Wayne Stark
 * @since 18.10.2022
 */
@Slf4j
@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException ex) {
        log.error("Response code: " + ex.getErrorCode(), ex);
        return Response
                .status(ex.getErrorCode())
                .entity(ex.getMessage())
                .build();
    }
}
