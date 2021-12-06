package com.example.eksamensprojekt.Services;


import com.example.eksamensprojekt.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    @Test
    public void makeUser() {
        Login login = new Login();
        String makeProfile = login.makeProfile("yovitus","greatestpassword");
        assertEquals("yovitus,greatestpassword", makeProfile);
    }

    @Test
    public void testSignup()
    {
        Login login = new Login();
        login.makeProfile("yovitus", "greatestpassword");
        User user = login.login("yovitus","greatestpassword");
        assertEquals("yovitus",user.username);
        assertEquals("greatestpassword",user.password);
    }
}
