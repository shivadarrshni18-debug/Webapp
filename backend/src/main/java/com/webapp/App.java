package com.webapp;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;

public class App {

    public static void main(String[] args) throws IOException {
        // Start a server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Define our registration endpoint
        server.createContext("/register", new RegistrationHandler());
        
        server.setExecutor(null); 
        server.start();
        System.out.println("Backend is running on http://localhost:8080");
    }

    static class RegistrationHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // 1. Handle CORS so our frontend can talk to this backend
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            // Handle preflight OPTIONS request
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // 2. Only allow POST requests for registration
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1); 
                exchange.close();
                return;
            }

            // 3. Read the incoming JSON body
            InputStream is = exchange.getRequestBody();
            String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // 4. Parse JSON using Gson
            Gson gson = new Gson();
            RegistrationRequest req = gson.fromJson(body, RegistrationRequest.class);

            // 5. Hash the password (using the utility we built)
            String hashedPassword = PasswordUtil.hashPassword(req.password);

            // Note: We are deliberately skipping the actual database INSERT here 
            // until you configure your MySQL database connection. 
            // UserRepository repo = new UserRepository();
            // repo.saveUser(conn, req.name, req.phone, req.email, hashedPassword);

            // 6. Send success response
            String response = "{\"status\":\"success\", \"message\":\"User registered successfully!\"}";
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(201, response.getBytes().length);
            
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            
            System.out.println("Processed registration for: " + req.email);
        }
    }
}