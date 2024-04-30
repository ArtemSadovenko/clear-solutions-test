package com.septemarch.test;

import com.septemarch.test.entitis.User;
import com.septemarch.test.exceptions.InvalidData;
import com.septemarch.test.properties.AgeProperties;
import com.septemarch.test.repository.UserRepository;
import com.septemarch.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    @Mock
    private AgeProperties ageProperties;

    @Test
    public void testCreateUserInvalidUserAge() {

        LocalDate today = LocalDate.now();
        int requiredAge = 18;
        LocalDate invalidBirthDate = today.minusYears(requiredAge - 1);
        User user = new User(1L, "John", "Doe", "john.doe@example.com", invalidBirthDate ,"Backer St.", "+380660916137");

        Mockito.when(ageProperties.getRequiredAge()).thenReturn(requiredAge);

        assertThrows(InvalidData.class, () -> userService.createUser(user));
    }


    @Test
    public void testCreateUserValidUser() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "Backer St.", "+380000000000");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        userService.createUser(user);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}

