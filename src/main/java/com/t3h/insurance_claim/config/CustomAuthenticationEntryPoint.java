package com.t3h.insurance_claim.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t3h.insurance_claim.exception.ApiExceptionResponse;
import com.t3h.insurance_claim.exception.CustomError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        ApiExceptionResponse<CustomError> apiExceptionResponse = ApiExceptionResponse.<CustomError>builder()
                .error(CustomError.builder()
                        .message(authException.getMessage())
                        .timestamp(new Date())
                        .path(request.getRequestURI())
                        .details("You need to authenticate or provide valid credentials to access this resource. Please recheck your information!")
                        .build())
                .statusCode(HttpServletResponse.SC_UNAUTHORIZED)
                .build();

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(mapper.writeValueAsString(apiExceptionResponse));

    }
}
