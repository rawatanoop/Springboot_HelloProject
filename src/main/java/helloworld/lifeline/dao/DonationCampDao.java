package helloworld.lifeline.dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import helloworld.lifeline.entity.DonationCamp;

@Repository
public class DonationCampDao /* implements IDonationCampDao<DonationCamp> */ {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	@Transactional
	public void save(DonationCamp camp) {
		Session session = getSession();
		session.save(camp);
		return;
	}

	@Transactional
	public void delete(DonationCamp camp) {
		getSession().delete(camp);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DonationCamp> getAll() {
		return getSession().createQuery("from DonationCamp").list();
	}

	@Transactional
	public DonationCamp getById(Integer id) throws ObjectNotFoundException {
		DonationCamp camp = getSession().load(DonationCamp.class, id);
		if (camp.getId() == id)
			return camp;
		return null;
	}

	@Transactional
	public void update(DonationCamp camp) {
		getSession().update(camp);
		return;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DonationCamp> getByAddress(String address) {
		Query query = getSession().createQuery("from DonationCamp where address=:address");
		query.setParameter("address", address);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DonationCamp> getByUserID(int userID) {
		List<DonationCamp> camps = getSession().getNamedQuery("DonationCamp.findByUserID").setInteger("userID", userID)
				.list();
		return camps;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DonationCamp> getByAddressCategory(String address, int category) {
		Query query = getSession().createQuery("from DonationCamp where address=:address and campCategoryID=:category");
		query.setParameter("address", address);
		query.setParameter("category", category);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<DonationCamp> getByCategoryID(int category) {

		List<DonationCamp> camps = getSession().getNamedQuery("DonationCamp.findByCampCategoryID")
				.setInteger("campCategoryID", category).list();
		return camps;
	}

}
