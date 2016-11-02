package helloworld.lifeline.model;

import java.util.Date;

public class DonationCampModel {
	private int id;
	private int userID;
	private int campCategoryID;
	private String address;
	private String start;
	private String end;
	private Date startDate;
	private Date endDate;
	private int unit;
	private int unitLeft;
	private String categoryName;
	private String subCategoryName;
	private int unitDonate;

	public int getUnitDonate() {
		return unitDonate;
	}

	public void setUnitDonate(int unitDonate) {
		this.unitDonate = unitDonate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getCampCategoryID() {
		return campCategoryID;
	}

	public void setCampCategoryID(int campCategoryID) {
		this.campCategoryID = campCategoryID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getUnitLeft() {
		return unitLeft;
	}

	public void setUnitLeft(int unitLeft) {
		this.unitLeft = unitLeft;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
}
