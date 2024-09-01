package com.t3h.insurance_claim.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t3h.insurance_claim.exception.ApiExceptionResponse;
import com.t3h.insurance_claim.exception.CustomError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ApiExceptionResponse<CustomError> apiExceptionResponse = ApiExceptionResponse.<CustomError>builder()
                .error(CustomError.builder()
                        .timestamp(new Date())
                        .path(request.getRequestURI())
                        .message(accessDeniedException.getMessage())
                        .details("Access is denied due to only ADMIN permission access those endpoints. Please recheck your information!")
                        .build())
                .statusCode(HttpServletResponse.SC_FORBIDDEN)
                .build();

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(apiExceptionResponse));
        response.flushBuffer();

    }
}
