package helloworld.lifeline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.model.UserModel;
import helloworld.lifeline.service.IUserService;
import inti.ws.spring.exception.client.BadRequestException;

@RestController
@RequestMapping(value = "/user")
@ComponentScan("helloworld.lifeline.service")
public class UserController {

	private static final Logger logger = Logger.getInstance(DonationCampController.class);

	@Autowired
	private IUserService userService;

	/***
	 * Returns the user associated with userId @param id
	 * 
	 * @param id
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public UserModel getByID(@PathVariable("id") int id) throws BadRequestException {
		logger.info("Request for geting a user with given id started");
		UserModel model = userService.getByID(id);
		logger.info("Request for geting a user with given id ended successfully");
		return model;
	}

	/****
	 * Creates a User @param user in the records.
	 * 
	 * @param user
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody UserModel user) {
		logger.info("Request for saving  a user started");
		userService.create(user);
		logger.info("Request for saving  a user started ended successfully");

	}

}
