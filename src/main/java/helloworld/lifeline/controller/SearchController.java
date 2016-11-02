package helloworld.lifeline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.service.DonationCampService;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@RestController
@RequestMapping(value = "/search")
@ComponentScan("helloworld.lifeline.service")
public class SearchController {

	@Autowired
	private DonationCampService dcService;

	private static final Logger logger = Logger.getInstance(SearchController.class);

	/***
	 * Return all the available donation camps.
	 * 
	 * @return
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/all")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getAll() throws NotFoundException, BadRequestException {
		logger.info("Request for all the camp started");
		List<DonationCampModel> list = dcService.getAll();
		logger.info("Request for all the camp ended successfully");
		return list;
	}

	/****
	 * This method returns all the camps with given @param address and @param
	 * category.
	 * 
	 * @param address
	 * @param category
	 * @return
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */

	@RequestMapping(value = "/filter")
	@ResponseBody
	public List<DonationCampModel> getByAddressCategory(String address, int category)
			throws NotFoundException, BadRequestException {

		logger.info("Request for all the camp with given address and category started");
		List<DonationCampModel> list = dcService.fillCategoryName(dcService.getByAddressCategory(address, category));
		logger.info("Request for all the camp with given address and category ened successfully");
		return list;
	}

}
