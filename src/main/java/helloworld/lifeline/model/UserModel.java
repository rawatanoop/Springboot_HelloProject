package helloworld.lifeline.model;

import java.math.BigInteger;

public class UserModel {
	private Integer id;
	private String name;
	private BigInteger mobile;
	private String email;
	private Integer unitDonate;
	private Integer campID;

	public Integer getUnitDonate() {
		return unitDonate;
	}

	public void setUnitDonate(Integer unitDonate) {
		this.unitDonate = unitDonate;
	}

	public Integer getCampID() {
		return campID;
	}

	public void setCampID(Integer campID) {
		this.campID = campID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getMobile() {
		return mobile;
	}

	public void setMobile(BigInteger mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
