package com.example.demo.model;

public class ApiResponseFactory {
    public static ApiResponse createApiResponse(int responseCode, Object data, String message) {
        return new ApiResponse(responseCode, data, message);
    }
}
