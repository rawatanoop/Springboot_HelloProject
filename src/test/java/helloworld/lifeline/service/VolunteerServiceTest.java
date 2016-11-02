package helloworld.lifeline.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.VolunteerModel;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = { TestDatabaseConfig.class })
public class VolunteerServiceTest {

	@Autowired
	VolunteerService volunteerService;

	@Test
	public void testCreate() throws BadRequestException {

		VolunteerModel volunteer = UtilityTest.getVolTestModel("Request", 10);
		try {
			volunteerService.create(volunteer);
		} catch (DataIntegrityViolationException e) {
		}
		Assert.assertNotNull(volunteerService.getByCampID(volunteer.getCampID()));
	}

	@Test
	public void testGetCampForUser() throws BadRequestException {
		try {
			volunteerService.create(UtilityTest.getVolTestModel("Request", 10));
		} catch (Exception e) {

		}
		List<DonationCampModel> list = volunteerService.getCampForUser(UtilityTest.UserID, "Request");
		Assert.assertNotNull(list);
	}

	@Test
	public void testGetByCampID() throws BadRequestException {
		List<VolunteerModel> list = volunteerService.getByCampID(UtilityTest.Camp_ID);
		for (VolunteerModel volunteerModel : list) {
			Assert.assertEquals(UtilityTest.Camp_ID, new Integer(volunteerModel.getCampID()));
		}
	}

	@Test(expected = BadRequestException.class)
	public void testGetByCampIDError() throws BadRequestException {
		volunteerService.getByCampID(-1);
	}

	@Test
	public void testUpdate() throws BadRequestException {
		try {
			volunteerService.create(UtilityTest.getVolTestModel("Request", 10));
		} catch (Exception e) {

		}
		VolunteerModel volunteer = UtilityTest.getVolTestModel("Request", 10);
		volunteer.setRequestStatus("Accept");
		volunteerService.update(volunteer);
		Assert.assertEquals(volunteer.getRequestStatus(), "Accept");
	}

	@Test(expected = BadRequestException.class)
	public void testUpdateError() throws BadRequestException{
		VolunteerModel volunteer = UtilityTest.getVolTestModel("WrongRequest", 10);
		volunteerService.update(volunteer);
	}

	@Test
	public void testGetVolunteerForCamp() throws NotFoundException {
		List<VolunteerModel> list = volunteerService.getVolunteerForCamp(UtilityTest.Camp_ID, "Request");
		for (VolunteerModel volunteerModel : list) {
			Assert.assertEquals(new Integer(volunteerModel.getCampID()), UtilityTest.Camp_ID);
		}
	}

	@Test(expected = NotFoundException.class)
	public void testGetVolunteerForCampError() throws NotFoundException {
		volunteerService.getVolunteerForCamp(UtilityTest.Camp_ID, "WrongStatus");

	}
}
