package helloworld.lifeline.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.UserModel;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = { TestDatabaseConfig.class })
public class DonationCampServiceTest {
	@Autowired
	DonationCampService dcService;

	@Test
	public void testCreate() {
		DonationCampModel testCampModel = UtilityTest.getDCTestModel();
		dcService.create(testCampModel);
	}

	@Test
	public void testGetAll() throws NotFoundException {
		Assert.assertNotNull(dcService.getAll());
	}

	@Test
	public void testGetByAddress() throws NotFoundException {
		List<DonationCampModel> campModels = dcService.getByAddress(UtilityTest.Camp_Address);
		for (DonationCampModel donationCampModel : campModels) {
			Assert.assertEquals(donationCampModel.getAddress(), UtilityTest.Camp_Address);
		}
	}

	@Test
	public void testGetByAddressCategory() throws NotFoundException {
		List<DonationCampModel> campModels = dcService.getByAddressCategory(UtilityTest.Camp_Address,
				UtilityTest.Category);
		for (DonationCampModel donationCampModel : campModels) {
			Assert.assertEquals(donationCampModel.getAddress(), UtilityTest.Camp_Address);
			Assert.assertEquals(new Integer(donationCampModel.getCampCategoryID()), UtilityTest.Category);
		}
	}

	@Test
	public void testGetByUserID() throws NotFoundException {
		List<DonationCampModel> campModels = dcService.getByUserID(UtilityTest.UserID);
		for (DonationCampModel donationCampModel : campModels) {
			Assert.assertEquals(new Integer(donationCampModel.getUserID()), UtilityTest.UserID);
		}
	}

	@Test
	public void testFillCategoryName() {
		ArrayList<DonationCampModel> list = new ArrayList<DonationCampModel>();
		DonationCampModel model = UtilityTest.getDCTestModel();
		list.add(model);
		dcService.fillCategoryName(list);
		Assert.assertEquals(model.getCategoryName(), UtilityTest.CategoryName_1);
		Assert.assertEquals(model.getSubCategoryName(), UtilityTest.SubCategoryName_1);

	}

	@Test(expected = NotFoundException.class)
	public void testGetVolunteerForCamp() throws NotFoundException, BadRequestException {
		 
		 List<UserModel> campModels = dcService.getVolunteerForCamp(UtilityTest.Camp_ID, "Request");
		Assert.assertNotNull(campModels);
	}

	@Test(expected = BadRequestException.class)
	public void testGetByIDError() throws ObjectNotFoundException, BadRequestException  {
		  dcService.getByID(-1);
		
	}
	
	@Test
	public void testUpdate() throws NotFoundException, ObjectNotFoundException, BadRequestException {
		List<DonationCampModel> campModels = dcService.getByUserID(1);
		DonationCampModel campModel = campModels.get(0);
		String address = campModel.getAddress();
		campModel.setAddress("Modified Address");
		dcService.update(campModel);
		DonationCampModel campModelMod = dcService.getByID(campModel.getId());
		Assert.assertEquals(campModelMod.getAddress(), "Modified Address");
		campModel.setAddress(address);
		dcService.update(campModel);
	}

}
