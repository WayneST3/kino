package dev.neiro.dental.serializer;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Wayne Stark
 * @since 18.10.2022
 */
@Slf4j
@Provider
public class InternalExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        log.error("Внутренняя ошибка сервера", ex);
        return Response
                .status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .entity("Внутренняя ошибка сервера")
                .build();
    }
}
