/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.lifeline.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anoop
 */
@Entity
@Table(name = "Donation_Camp_Volunteer")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DonationCampVolunteer.findAll", query = "SELECT d FROM DonationCampVolunteer d"),
		@NamedQuery(name = "DonationCampVolunteer.findByUserID", query = "SELECT d FROM DonationCampVolunteer d WHERE d.donationCampVolunteerPK.userID = :userID"),
		@NamedQuery(name = "DonationCampVolunteer.findByCampID", query = "SELECT d FROM DonationCampVolunteer d WHERE d.donationCampVolunteerPK.campID = :campID"),
		@NamedQuery(name = "DonationCampVolunteer.findByRequestStatus", query = "SELECT d FROM DonationCampVolunteer d WHERE d.requestStatus = :requestStatus") })
public class DonationCampVolunteer implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected DonationCampVolunteerPK donationCampVolunteerPK;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "Request_Status")
	private String requestStatus;
	@NotNull
	@Column(name = "Unit_Donate")
	private int unitDonate;

	public int getUnitDonate() {
		return unitDonate;
	}

	public void setUnitDonate(int unitDonate) {
		this.unitDonate = unitDonate;
	}

	public DonationCampVolunteer() {
	}

	public DonationCampVolunteer(DonationCampVolunteerPK donationCampVolenteerPK) {
		this.donationCampVolunteerPK = donationCampVolenteerPK;
	}

	public DonationCampVolunteer(DonationCampVolunteerPK donationCampVolenteerPK, String requestStatus) {
		this.donationCampVolunteerPK = donationCampVolenteerPK;
		this.requestStatus = requestStatus;
	}

	public DonationCampVolunteer(int userID, int campID) {
		this.donationCampVolunteerPK = new DonationCampVolunteerPK(userID, campID);
	}

	public DonationCampVolunteerPK getDonationCampVolenteerPK() {
		return donationCampVolunteerPK;
	}

	public void setDonationCampVolenteerPK(DonationCampVolunteerPK donationCampVolenteerPK) {
		this.donationCampVolunteerPK = donationCampVolenteerPK;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (donationCampVolunteerPK != null ? donationCampVolunteerPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DonationCampVolunteer)) {
			return false;
		}
		DonationCampVolunteer other = (DonationCampVolunteer) object;
		if ((this.donationCampVolunteerPK == null && other.donationCampVolunteerPK != null)
				|| (this.donationCampVolunteerPK != null
						&& !this.donationCampVolunteerPK.equals(other.donationCampVolunteerPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entity.DonationCampVolenteer[ donationCampVolenteerPK=" + donationCampVolunteerPK + " ]";
	}

}
