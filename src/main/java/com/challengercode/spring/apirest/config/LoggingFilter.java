package com.challengercode.spring.apirest.config;

import com.challengercode.spring.apirest.model.CallHistory;
import com.challengercode.spring.apirest.repository.CallHistoryRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingFilter implements Filter {

    private final CallHistoryRepository repository;

    public LoggingFilter(CallHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest && response instanceof HttpServletResponse httpResponse) {
            ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpResponse);

            // Capturar los parámetros enviados
            Map<String, String[]> parameterMap = httpRequest.getParameterMap();
            Map<String, String> requestParams = new HashMap<>();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                requestParams.put(entry.getKey(), String.join(",", entry.getValue()));
            }

            try {
                // Permitir que la petición pase al siguiente filtro o controlador
                chain.doFilter(request, wrappedResponse);
            } finally {
                // Leer el contenido de la respuesta después de ejecutarla
                String responseBody = new String(wrappedResponse.getContentAsByteArray());
                wrappedResponse.copyBodyToResponse(); // ✅ Reescribir la respuesta para que no se pierda

                // Guardar en la base de datos
                CallHistory history = new CallHistory();
                history.setTimestamp(LocalDateTime.now());
                history.setEndpoint(httpRequest.getRequestURI());
                history.setRequestParams(requestParams.toString());
                history.setResponse(responseBody);
                history.setStatusCode(httpResponse.getStatus());

                repository.save(history);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
