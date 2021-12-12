package com.example.eksamensprojekt.Services;


import com.example.eksamensprojekt.Models.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    /*@Test
    public void testMakeProfile() {
        Login login = new Login();
        var value = login.makeProfile("yovitus","greatestpassword");
        assertEquals("yovitusgreatestpassword", "");
    }*/

    @Test
    public void testLogin() throws IOException {
        Login lg = new Login();
        lg.makeProfile("yovitus", "greatestpassword");
        User user = lg.login("yovitus", "greatestpassword");
        assertEquals("yovitus", user.username);
        assertEquals("greatestpassword", user.password);
    }
}