package helloworld.lifeline.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import helloworld.lifeline.entity.DonationCampCategory;

@Repository
public class CategoryDao implements ICategoryDao<DonationCampCategory> {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}


	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<DonationCampCategory> getAll() {
		return getSession().createQuery("from DonationCampCategory").list();
	}

	@Override
	@Transactional
	public DonationCampCategory getById(Integer categoryID) {
		DonationCampCategory category = getSession().load(DonationCampCategory.class, categoryID);
		if (category.getId() == categoryID)
			return category;
		return null;
	}


	@Override
	public Serializable save(DonationCampCategory entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(DonationCampCategory entity) {
		
	}


	@Override
	public void update(DonationCampCategory entity) {
		
	}


}
