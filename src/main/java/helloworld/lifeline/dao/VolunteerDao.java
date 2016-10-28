package helloworld.lifeline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import helloworld.lifeline.entity.DonationCampVolunteer;

@Repository
public class VolunteerDao implements IVolunteerDao<DonationCampVolunteer> {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@Override
	@Transactional
	public Serializable save(DonationCampVolunteer volunteer) throws DataIntegrityViolationException {

		return getSession().save(volunteer);
	}

	@Override
	@Transactional
	public void delete(DonationCampVolunteer volunteer) {
		getSession().delete(volunteer);
		return;
	}

	@Override
	@Transactional
	public DonationCampVolunteer getById(Integer id) {
		DonationCampVolunteer volunteer = getSession().load(DonationCampVolunteer.class, id);
		return volunteer;
	}

	@Override
	@Transactional
	public void update(DonationCampVolunteer volunteer) {
		getSession().update(volunteer);
		return;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DonationCampVolunteer> getAllByCampID(int campID) {
		List<DonationCampVolunteer> volunteers = getSession().getNamedQuery("DonationCampVolunteer.findByCampID")
				.setInteger("campID", campID).list();
		return volunteers;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DonationCampVolunteer> getAllByUserID(int userID) {
		List<DonationCampVolunteer> volunteers = getSession().getNamedQuery("DonationCampVolunteer.findByUserID")
				.setInteger("userID", userID).list();
		return volunteers;
	}

	@Override
	@Deprecated
	public List<DonationCampVolunteer> getAll() {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<DonationCampVolunteer> getAllByUserStatus(int userID, String status) {
		Query query = getSession().createQuery(
				"from DonationCampVolunteer where donationCampVolunteerPK.userID=:userID and requestStatus=:requestStatus");
		query.setParameter("userID", userID);
		query.setParameter("requestStatus", status);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<DonationCampVolunteer> getVolunteerForCamp(int campID, String status) {
		Query query = getSession().createQuery(
				"from DonationCampVolunteer where donationCampVolunteerPK.campID=:campID and requestStatus=:requestStatus");
		query.setParameter("campID", campID);
		query.setParameter("requestStatus", status);
		return query.list();
	}

}
