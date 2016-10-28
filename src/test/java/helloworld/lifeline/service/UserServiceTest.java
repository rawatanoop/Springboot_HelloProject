package helloworld.lifeline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import inti.ws.spring.exception.client.BadRequestException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = { TestDatabaseConfig.class })
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test(expected = BadRequestException.class)
	public void testGetByIDError() throws BadRequestException {
		userService.getByID(-1);
	}

	@Test
	public void testIsValidUser() {
		Assert.assertNotNull(userService.isValidUser(UtilityTest.User_Email));
		Assert.assertNull(userService.isValidUser(UtilityTest.User_Email + "2"));
	}

	@Test
	public void testGetById() throws BadRequestException {
		Assert.assertNotNull(userService.getByID(UtilityTest.UserID));
	}
}
