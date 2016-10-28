/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.lifeline.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author anoop
 */
@Embeddable
public class DonationCampVolunteerPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1149246691235954873L;
	@Basic(optional = false)
	@NotNull
	@Column(name = "User_ID")
	private int userID;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Camp_ID")
	private int campID;

	public DonationCampVolunteerPK() {
	}

	public DonationCampVolunteerPK(int userID, int campID) {
		this.userID = userID;
		this.campID = campID;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += userID;
		hash += campID;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DonationCampVolunteerPK)) {
			return false;
		}
		DonationCampVolunteerPK other = (DonationCampVolunteerPK) object;
		if (this.userID != other.userID) {
			return false;
		}
		if (this.campID != other.campID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entity.DonationCampVolunteerPK[ userID=" + userID + ", campID=" + campID + " ]";
	}

}
