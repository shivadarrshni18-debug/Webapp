package com.webapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryTest{
    @Test
    public void testInsertSql(){
        String expectedSql = "INSERT INTO users (name, phone, email, password_hash) VALUES (?, ?, ?, ?)";
        assertEquals(expectedSql, UserRepository.INSERT_SQL);
    }
}