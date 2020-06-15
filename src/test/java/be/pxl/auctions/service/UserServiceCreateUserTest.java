package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserDao;
import be.pxl.auctions.model.User;
import be.pxl.auctions.util.exception.DuplicateEmailException;
import be.pxl.auctions.util.exception.InvalidDateException;
import be.pxl.auctions.util.exception.InvalidEmailException;
import be.pxl.auctions.util.exception.RequiredFieldException;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;


public class UserServiceCreateUserTest {

	// TODO add unit tests for all possible scenario's of the createUser method
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;
    private User user;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setFirstName("Bogaert");
        user.setLastName("Lenaers");
        user.setDateOfBirth(LocalDate.of(2020, 5, 8));
        user.setEmail("balou@beardeddragon.be");
    }
    @Test
    public void throwsRequiredFieldExceptionWhenFirstNameIsBlank(){

       user.setFirstName("");
        assertThrows(RequiredFieldException.class, () -> userService.createUser(user));
    }

    @Test
    public void throwsRequiredFieldExceptionWhenLastNameIsBlank(){
        user.setLastName("");
        assertThrows(RequiredFieldException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsRequiredFieldExceptionWhenEmailIsBlank(){
        user.setEmail("");
        assertThrows(RequiredFieldException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsInvalidEmailExceptionWhenEmailIsBlank(){
        user.setEmail("baloubeardeddragon.be");
        assertThrows(InvalidEmailException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsRequiredFieldExceptionWhenDateOfBirthIsNull(){
        user.setDateOfBirth(null);
        assertThrows(RequiredFieldException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsInvalidDateExceptionWhenDateIsAfterNow(){
        user.setDateOfBirth(LocalDate.now().plusDays(1));
        assertThrows(InvalidDateException.class, () ->userService.createUser(user));
    }
    @Test
    public void throwsDuplicateEmailExceptionWhenUserAlreadyExists(){
        when(userDao.findUserByEmail(user.getEmail())).thenReturn(user);
        assertThrows(DuplicateEmailException.class, () ->userService.createUser(user));
    }
    @Test
    public void userIsSavedWhenUserIsValid() throws InvalidDateException, RequiredFieldException, InvalidEmailException, DuplicateEmailException {
        when(userDao.findUserByEmail(user.getEmail())).thenReturn(null);
        when(userDao.saveUser(user)).thenReturn(user);
        assertDoesNotThrow(() -> userService.createUser(user));
        assertEquals(user ,userService.createUser(user));

    }
}
