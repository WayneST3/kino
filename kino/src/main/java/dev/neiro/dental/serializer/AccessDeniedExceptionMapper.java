package dev.neiro.dental.serializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;

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
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    @Override
    public Response toResponse(AccessDeniedException ex) {
        log.error("Доступ запрещен", ex);
        return Response
                .status(HttpServletResponse.SC_FORBIDDEN)
                .entity(ex.getMessage())
                .build();
    }
}
