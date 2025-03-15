package com.challengercode.spring.apirest.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "call_history")
public class CallHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String endpoint;

    @Column(columnDefinition = "TEXT")
    private String requestParams;

    @Column(columnDefinition = "TEXT")
    private String response;

    private int statusCode;

    // ✅ Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public String getRequestParams() { return requestParams; }
    public void setRequestParams(String requestParams) { this.requestParams = requestParams; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
}
