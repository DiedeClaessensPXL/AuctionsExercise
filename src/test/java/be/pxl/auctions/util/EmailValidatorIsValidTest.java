package be.pxl.auctions.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorIsValidTest {

	@Test
	public void returnsTrueWhenValidEmail() {
		// TODO implement test
		assertTrue(EmailValidator.isValid("balou@beardeddragon.be"));
	}

	@Test
	public void returnsFalseWhenAtSignMissing() {
		// TODO implement test
		assertFalse(EmailValidator.isValid("baloubeardeddragon.be"));
	}

}
