package helloworld.lifeline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import helloworld.lifeline.model.CategoryModel;
import helloworld.lifeline.service.ICategoryService;

@RestController
@RequestMapping(value = "/category")
@ComponentScan("helloworld.lifeline.service")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;

	/**
	 * Return all the available categories.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all",method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryModel> getAll() {

		return categoryService.getAll();
	}

}
