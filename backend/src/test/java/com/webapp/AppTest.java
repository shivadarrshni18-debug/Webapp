package com.webapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest{

    @Test
    public void testAppStatus(){
        assertEquals("Backend is running",App.getStatusMessage());
    }
}