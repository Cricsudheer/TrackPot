package com.example.trackpot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for health checks.
 */
@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
@Tag(name = "Health", description = "Health check API")
public class HealthCheckController {

    private final JdbcTemplate jdbcTemplate;

    /**
     * GET /api/health/db : Check database connection
     *
     * @return the ResponseEntity with status 200 (OK) if database is connected, or status 503 (Service Unavailable) if not
     */
    @GetMapping("/db")
    @Operation(summary = "Check database connection", description = "Tests the database connection by executing a simple query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Database connection is working"),
            @ApiResponse(responseCode = "503", description = "Database connection failed")
    })
    public ResponseEntity<Map<String, Object>> checkDatabaseConnection() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Execute a simple query to test the connection
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            
            response.put("status", "UP");
            response.put("message", "Database connection is working");
            response.put("details", Map.of(
                    "test_query", "SELECT 1",
                    "result", result
            ));
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "DOWN");
            response.put("message", "Database connection failed");
            response.put("error", e.getMessage());
            
            return ResponseEntity.status(503).body(response);
        }
    }
}