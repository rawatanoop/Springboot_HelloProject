package helloworld.lifeline.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.UserModel;
import helloworld.lifeline.model.VolunteerModel;
import helloworld.lifeline.service.DonationCampService;
import helloworld.lifeline.service.IVolunteerService;
import inti.ws.spring.exception.client.BadRequestException;

@Controller
@RequestMapping(value = "/volunteer")
@ComponentScan("helloworld.lifeline.service")
public class VolunteerController {

	@Autowired
	private IVolunteerService volunteerService;

	@Autowired
	private DonationCampService dcService;
	private static final Logger logger = Logger.getInstance(LoginController.class);

	/****
	 * Returns the camp associated with amp id @param id
	 * 
	 * @param id
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public DonationCampModel getByCampID(@PathVariable("id") int id) throws BadRequestException {
		logger.info("Request for geting a cmp with given id started");
		DonationCampModel model = dcService.getByID(id);
		logger.info("Request for geting a cmp with given id ended successfully");
		return model;

	}

	/****
	 * Creates a record with given @param model for a volunteer request.
	 * 
	 * @param model
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody VolunteerModel model) throws BadRequestException {
		logger.info("Request for creating a volunteer record started");
		volunteerService.create(model);
		logger.info("Request for creating a volunteer record  ended successfully");

	}

	/****
	 * Updates a volunteer record with updated information @param model
	 * 
	 * @param model
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody VolunteerModel model) throws BadRequestException {
		logger.info("Request for updating a volunteer record started");
		volunteerService.update(model);
		logger.info("Request for updating a volunteer record  ended successfully");

	}

	/***
	 * Return all the camps details(with status 'Accept') for a particular
	 * volunteer.
	 * 
	 * @param userID
	 * @param session
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "{id}/acceptedRequest")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getAccptedReq(@PathVariable("id") int userID, HttpSession session)
			throws BadRequestException {
		logger.info("Request with status 'Accept' for returning camp details for a volunteer started");
		List<DonationCampModel> list = volunteerService
				.getCampForUser(((UserModel) session.getAttribute("user")).getId(), "Accept");
		logger.info("Request with status 'Accept' for returning camp details for a volunteer ended successfully");
		return list;
	}

	/***
	 * Return all the camps details(with status 'Request') for a particular
	 * volunteer.
	 * 
	 * @param userID
	 * @param session
	 * @return
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "{id}/pendingRequest")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getPendingReq(@PathVariable("id") int userID, HttpSession session)
			throws BadRequestException {
		logger.info("Request with status 'Request' for returning camp details for a volunteer started");
		List<DonationCampModel> list = volunteerService
				.getCampForUser(((UserModel) session.getAttribute("user")).getId(), "Request");
		logger.info("Request  with status 'Request' for returning camp details for a volunteer ended successfully");
		return list;
	}

}
