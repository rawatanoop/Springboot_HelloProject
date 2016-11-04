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
	private static final Logger logger = Logger.getInstance(VolunteerController.class);


	/****
	 * Creates a record with given @param model for a volunteer request.
	 * 
	 * @param model
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody VolunteerModel model, HttpSession session) throws BadRequestException {
		logger.info("Request for creating a volunteer record started");
		model.setUserID(((UserModel) session.getAttribute("user")).getId());
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
	@RequestMapping(value = "/acceptedRequest")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getAccptedReq( HttpSession session)
			throws BadRequestException {
		logger.info("Request with status 'Accept' for the volunteer started");
		List<DonationCampModel> list = volunteerService
				.getCampForUser(((UserModel) session.getAttribute("user")).getId(), "Accept");
		logger.info("Request with status 'Accept' for the volunteer ended successfully");
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
	@RequestMapping(value = "/pendingRequest")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getPendingReq( HttpSession session)
			throws BadRequestException {
		logger.info("Request with status 'Request' for returning camp details for a volunteer started");
		List<DonationCampModel> list = volunteerService
				.getCampForUser(((UserModel) session.getAttribute("user")).getId(), "Request");
		logger.info("Request  with status 'Request' for returning camp details for a volunteer ended successfully");
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
	@RequestMapping(value = "/rejectedRequest")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getRejectedReq(HttpSession session)
			throws BadRequestException {
		logger.info("Request with status 'Reject' for returning camp details for a volunteer started");
		List<DonationCampModel> list = volunteerService
				.getCampForUser(((UserModel) session.getAttribute("user")).getId(), "Reject");
		logger.info("Request  with status 'Reject' for returning camp details for a volunteer ended successfully");
		return list;
	}

}
