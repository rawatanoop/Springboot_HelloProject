package helloworld.lifeline.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author anoop
 */
@Entity
@Table(name = "Donation_Camp")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DonationCamp.findAll", query = "SELECT d FROM DonationCamp d"),
		@NamedQuery(name = "DonationCamp.findById", query = "SELECT d FROM DonationCamp d WHERE d.id = :id"),
		@NamedQuery(name = "DonationCamp.findByUserID", query = "SELECT d FROM DonationCamp d WHERE d.userID = :userID"),
		@NamedQuery(name = "DonationCamp.findByCampCategoryID", query = "SELECT d FROM DonationCamp d WHERE d.campCategoryID = :campCategoryID"),
		@NamedQuery(name = "DonationCamp.findByStartDate", query = "SELECT d FROM DonationCamp d WHERE d.startDate = :startDate"),
		@NamedQuery(name = "DonationCamp.findByEndDate", query = "SELECT d FROM DonationCamp d WHERE d.endDate = :endDate"),
		@NamedQuery(name = "DonationCamp.findByUnit", query = "SELECT d FROM DonationCamp d WHERE d.unit = :unit"),
		@NamedQuery(name = "DonationCamp.findByUnitLeft", query = "SELECT d FROM DonationCamp d WHERE d.unitLeft = :unitLeft") })
public class DonationCamp implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "User_ID")
	private int userID;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Camp_Category_ID")
	private int campCategoryID;
	@Basic(optional = false)
	@NotNull
	@Lob
	@Size(min = 1, max = 65535)
	@Column(name = "Address")
	private String address;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Start_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Basic(optional = false)
	@NotNull
	@Column(name = "End_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Unit")
	private int unit;
	@Basic(optional = false)
	@NotNull
	@Column(name = "Unit_Left")
	private int unitLeft;

	public DonationCamp() {
	}

	public DonationCamp(Integer id) {
		this.id = id;
	}

	public DonationCamp(Integer id, int userID, int campCategoryID, String address, Date startDate, Date endDate,
			int unit, int unitLeft) {
		this.id = id;
		this.userID = userID;
		this.campCategoryID = campCategoryID;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.unit = unit;
		this.unitLeft = unitLeft;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DonationCamp)) {
			return false;
		}
		DonationCamp other = (DonationCamp) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entity.DonationCamp[ id=" + id + " ]";
	}

}
