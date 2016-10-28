package helloworld.lifeline.service;

import java.util.List;

import helloworld.lifeline.model.CategoryModel;

public interface ICategoryService {

	/**
	 * Returns the details of a category
	 * 
	 * @param categoryID
	 * @return
	 */
	public CategoryModel getByID(int categoryID);

	/**
	 * Returns all the categories available in the records.
	 * 
	 * @return
	 */
	public List<CategoryModel> getAll();

}
