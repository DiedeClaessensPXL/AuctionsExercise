package be.pxl.auctions.dao.impl;

import be.pxl.auctions.dao.impl.util.DaoTest;
import be.pxl.auctions.model.User;
import org.hibernate.id.GUIDGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoImplTest extends DaoTest {

	private UserDaoImpl userDao = new UserDaoImpl(em);

	@Test
	public void allUsersCanBeRetrieved() {
		// TODO implement this test
		// create and save one user
		User user = new User();
		user.setFirstName("Bogaert");
		user.setLastName("Lenaers");
		user.setDateOfBirth(LocalDate.of(2020, 1, 14));
		user.setEmail("bogaert@beardeddragon.be");
		clear();
		User savedUser = userDao.saveUser(user);
		List<User> allUsers = userDao.findAllUsers();
		// retrieve all users

		allUsers.forEach(u -> AssertUserExists(user, u));

		// make sure there is at least 1 user in the list
		// make sure the newly created user is in the list (e.g. test if a user with this email address is in the list)
	}

	@Test
	public void userCanBeSavedAndRetrievedById() {
		User user = new User();
		user.setFirstName("Mark");
		user.setLastName("Zuckerberg");
		user.setDateOfBirth(LocalDate.of(1989, 5, 3));
		user.setEmail("mark@facebook.com");
		long newUserId = userDao.saveUser(user).getId();
		clear();

		User retrievedUser = userDao.findUserById(newUserId);
		AssertUserExists(user, retrievedUser);

	}

	@Test
	public void userCanBeSavedAndRetrievedByEmail() {
		// TODO implement this test
		User user = new User();
		user.setFirstName("Balou");
		user.setLastName("Claessens");
		user.setDateOfBirth(LocalDate.of(2020, 5, 8));
		user.setEmail("balou@beardeddragon.be");
		String email = userDao.saveUser(user).getEmail();

		clear();
		User retrievedUser = userDao.findUserByEmail(email);
		AssertUserExists(user ,retrievedUser);
	}


	@Test
	public void returnsNullWhenNoUserFoundWithGivenEmail() {
		// TODO implement this test
		User retrievedUser = userDao.findUserByEmail(new GUIDGenerator().toString());
		assertNull(retrievedUser);
	}



	private void AssertUserExists(User savedUser ,User retrievedUser) {
		assertNotNull(retrievedUser);
		assertEquals(savedUser.getFirstName(), retrievedUser.getFirstName());
		assertEquals(savedUser.getLastName(), retrievedUser.getLastName());
		assertEquals(savedUser.getEmail(), retrievedUser.getEmail());
		assertEquals(savedUser.getDateOfBirth(), retrievedUser.getDateOfBirth());
	}



}
