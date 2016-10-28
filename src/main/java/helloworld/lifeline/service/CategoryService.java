package helloworld.lifeline.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helloworld.lifeline.controller.LoginController;
import helloworld.lifeline.dao.ICategoryDao;
import helloworld.lifeline.entity.DonationCampCategory;
import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.model.CategoryModel;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryDao<DonationCampCategory> categoryDao;
	private static final Logger logger = Logger.getInstance(LoginController.class);

	/***
	 * Returns the category information for the @param userID
	 */
	@Override
	public CategoryModel getByID(int userID) {
		logger.info("Request for geting a category details with given id started");
		CategoryModel model = getModel(categoryDao.getById(userID));
		logger.info("Request for geting a category details with given id ended successfully");
		return model;

	}

	/****
	 * Returns all the categories available in the records.
	 */
	@Override
	public List<CategoryModel> getAll() {
		logger.info("Request for geting all categories details started");
		List<CategoryModel> list = new ArrayList<CategoryModel>();
		List<DonationCampCategory> entitylist = categoryDao.getAll();

		for (Iterator<DonationCampCategory> iterator = entitylist.iterator(); iterator.hasNext();) {
			list.add(getModel(iterator.next()));

		}
		logger.info("Request for geting all categories details ended successfully");
		return list;
	}

	/****
	 * Converts a entity into model
	 * 
	 * @param entity
	 * @return
	 */
	private CategoryModel getModel(DonationCampCategory entity) {
		CategoryModel model = new CategoryModel();
		model.setSubCategory(entity.getSubCategory());
		model.setId(entity.getId());
		model.setCategory(entity.getCategory());
		return model;

	}

}
