package be.pxl.auctions.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserGetAgeTest {


	@Test
	public void returnsCorrectAgeWhenHavingBirthdayToday() {
		// TODO implement test
		User user = new User();
		user.setDateOfBirth(LocalDate.now().minusYears(5));
		assertEquals(5, user.getAge());

	}

	@Test
	public void returnsCorrectAgeWhenHavingBirthdayTomorrow() {
		// TODO implement test
		User user = new User();
		user.setDateOfBirth(LocalDate.now().plusDays(1).minusYears(5));

		assertEquals(4, user.getAge());

	}

	@Test
	public void returnsCorrectAgeWhenBirthdayWasYesterday() {
		// TODO implement test
		User user = new User();
		user.setDateOfBirth(LocalDate.now().minusDays(1).minusYears(5));
		assertEquals(5, user.getAge());

	}

}
