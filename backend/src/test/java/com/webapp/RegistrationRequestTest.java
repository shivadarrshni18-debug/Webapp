package com.webapp;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationRequestTest {

    @Test
    public void testJsonParsing() {
        String json = "{\"name\":\"John Doe\", \"phone\":\"555-1234\", \"email\":\"john@example.com\", \"password\":\"secret\"}";
        
        Gson gson = new Gson();
        RegistrationRequest req = gson.fromJson(json, RegistrationRequest.class);
        
        assertEquals("John Doe", req.name);
        assertEquals("555-1234", req.phone);
        assertEquals("john@example.com", req.email);
        assertEquals("secret", req.password);
    }
}