package helloworld.lifeline.service;

import java.util.Date;

import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.VolunteerModel;

public class UtilityTest {
	public static final Integer Category = 1;
	public static final String CategoryName_1 = "Blood";
	public static final String SubCategoryName_1 = "A";
	public static final String User_Email = "anoop.singh@practo.com";
	public static final String Camp_Address = "testaddress";
	public static final Date StartDate = new Date(1111111111);
	public static final Date EndDate = new Date(1111111111);
	public static final Integer Camp_ID = 1;
	public static final Integer UserID = 1;

	static DonationCampModel getDCTestModel() {
		DonationCampModel campModel = new DonationCampModel();
		campModel.setAddress(Camp_Address);
		campModel.setCampCategoryID(Category);
		campModel.setEndDate(new Date(1111111111));
		campModel.setStartDate(new Date(1111111111));
		campModel.setUnit(100);
		campModel.setUnitLeft(100);
		campModel.setUserID(UserID);
		campModel.setId(Camp_ID);
		return campModel;
	}

	static VolunteerModel getVolTestModel(String status, int unitDonate) {
		VolunteerModel volunteer = new VolunteerModel();
		volunteer.setCampID(Camp_ID);
		volunteer.setUserID(UserID);
		volunteer.setRequestStatus(status);
		volunteer.setUnitDonate(unitDonate);
		return volunteer;

	}

}
