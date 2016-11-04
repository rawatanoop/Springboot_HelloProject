package helloworld.lifeline.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.UserModel;
import helloworld.lifeline.service.DonationCampService;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@RestController
@RequestMapping(value = "/donationCamp")
@ComponentScan("helloworld.lifeline.service")
public class DonationCampController {

	private static final Logger logger = Logger.getInstance(DonationCampController.class);

	@Autowired
	private DonationCampService dcService;

	/**
	 * Saves the @DonationCampModel and initializes the units-left with required
	 * units.
	 * 
	 * @param camp
	 * @param session
	 * @throws BadRequestException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody DonationCampModel camp, HttpSession session)
			throws BadRequestException, ParseException {
		logger.info("Request for creating  a camp started");
		SimpleDateFormat dateFrmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		camp.setStartDate(dateFrmt.parse(camp.getStart()));
		camp.setEndDate(dateFrmt.parse(camp.getEnd()));
		camp.setUserID(((UserModel) session.getAttribute("user")).getId());
		camp.setUnitLeft(camp.getUnit());
		dcService.create(camp);
		logger.info("Request for creating  a camp successfully ended");
	}


	/****
	 * Returns all the camps created by the user.
	 * 
	 * @param session
	 * @return
	 * @throws BadRequestException
	 * @throws NotFoundException
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public List<DonationCampModel> getByUserID(HttpSession session) throws BadRequestException, NotFoundException {
		logger.info("Request for camps created by the user started");
		List<DonationCampModel> list = dcService.getByUserID(((UserModel) session.getAttribute("user")).getId());
		logger.info("Request for camps created by the user ended successfully");
		return list;

	}

	/***
	 * Returns all the volunteers for the camp @param campID with @param
	 * requestStatus.
	 * 
	 * @param campID
	 * @param requestStatus
	 * @param session
	 * @return
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
	@RequestMapping(value = "/volunteer")
	@ResponseStatus(HttpStatus.OK)
	public List<UserModel> getVolunteerForCamp(int campID, String requestStatus, HttpSession session)
			throws NotFoundException, BadRequestException {
		logger.info("Request for getting  a volunteer for the camp  campID with requestStatus started");
		List<UserModel> list = dcService.getVolunteerForCamp(campID, requestStatus);
		logger.info("Request for getting  a volunteer for the camp  campID with requestStatus successfully ended");
		return list;

	}

	/***
	 * Handles all the exception associated with @DonationCampController .
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("error");
		return mav;
	}

}
