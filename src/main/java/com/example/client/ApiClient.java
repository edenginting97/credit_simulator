package com.example.client;

import com.example.dto.VehicleLoan;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private final HttpClient client = HttpClient.newHttpClient();

    public VehicleLoan get(String uri) throws Exception {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        var body = response.body();

        var mapper = new ObjectMapper();
        return mapper.readValue(body, VehicleLoan.class);
    }
}
