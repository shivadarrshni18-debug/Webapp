package com.webapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilTest {
    @Test
    public void testPasswordHashing() {
        String plainPassword = "mysecretpassword123";
        String hash =PasswordUtil.hashPassword(plainPassword);
        assertNotNull(hash);
        assertNotEquals(plainPassword, hash);
        //verify correct password 
        assertTrue(PasswordUtil.checkPassword(plainPassword, hash));
        //verify incorrect password
        assertFalse(PasswordUtil.checkPassword("wrongPassword", hash));
    }
}