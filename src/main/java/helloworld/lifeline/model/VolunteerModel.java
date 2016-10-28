package helloworld.lifeline.model;

public class VolunteerModel {
	private int userID;
	private int campID;
	private String requestStatus;
	private int unitDonate;

	public int getUnitDonate() {
		return unitDonate;
	}

	public void setUnitDonate(int unitDonate) {
		this.unitDonate = unitDonate;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCampID() {
		return campID;
	}

	public void setCampID(int campID) {
		this.campID = campID;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

}
