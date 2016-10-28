/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloworld.lifeline.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "Donation_Camp_Category")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DonationCampCategory.findAll", query = "SELECT d FROM DonationCampCategory d"),
		@NamedQuery(name = "DonationCampCategory.findById", query = "SELECT d FROM DonationCampCategory d WHERE d.id = :id"),
		@NamedQuery(name = "DonationCampCategory.findByCategory", query = "SELECT d FROM DonationCampCategory d WHERE d.category = :category"),
		@NamedQuery(name = "DonationCampCategory.findBySubCategory", query = "SELECT d FROM DonationCampCategory d WHERE d.subCategory = :subCategory") })
public class DonationCampCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 256)
	@Column(name = "Category")
	private String category;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 256)
	@Column(name = "Sub_Category")
	private String subCategory;

	public DonationCampCategory() {
	}

	public DonationCampCategory(Integer id) {
		this.id = id;
	}

	public DonationCampCategory(Integer id, String category, String subCategory) {
		this.id = id;
		this.category = category;
		this.subCategory = subCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
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
		if (!(object instanceof DonationCampCategory)) {
			return false;
		}
		DonationCampCategory other = (DonationCampCategory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entity.DonationCampCategory[ id=" + id + " ]";
	}

}
