// src/main/java/com/example/service/UserService.java
package com.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import io.smallrye.mutiny.Uni;

import com.example.model.Joke;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JokeService {

    private static final String API_URL = "https://api.chucknorris.io/jokes/random";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Uni<String> getRandomJoke() {
        return Uni.createFrom().item(() -> {
            try {
                URI uri = URI.create(API_URL);
                URL url = uri.toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder content = new StringBuilder();
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }

                    in.close();
                    connection.disconnect();

                    // Convert JSON response to Joke object
                    Joke joke = objectMapper.readValue(content.toString(), Joke.class);
                    return joke.getValue();
                } else {
                    throw new RuntimeException("Failed to retrieve joke: " + responseCode);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to retrieve joke", e);
            }
        });
    }
}
