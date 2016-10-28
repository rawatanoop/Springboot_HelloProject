package helloworld.lifeline.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import helloworld.lifeline.model.CategoryModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestComponent
@ContextConfiguration(classes = { TestDatabaseConfig.class })
public class CategoryServiceTest {

	@Autowired
	CategoryService catService;

	@Test
	public void testGetByID() {
		CategoryModel catModel = catService.getByID(UtilityTest.Category);
		Assert.assertEquals(new Integer(catModel.getId()), UtilityTest.Category);
	}

	@Test
	public void testGetAll() {
		List<CategoryModel> catList = catService.getAll();
		Assert.assertNotNull(catList);
	}

}
