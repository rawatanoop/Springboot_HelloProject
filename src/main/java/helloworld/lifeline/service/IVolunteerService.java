package helloworld.lifeline.service;

import java.util.List;

import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.VolunteerModel;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

public interface IVolunteerService {
	/**
	 * Creates a record for a volunteer in the database
	 * 
	 * @param volunteer
	 * @throws BadRequestException
	 */
	public void create(VolunteerModel volunteer) throws BadRequestException;

	/***
	 * Returns all the camps for a user @param userID where user status
	 * is @param status.
	 * 
	 * @param userID
	 * @param string
	 * @return
	 * @throws BadRequestException
	 */
	public List<DonationCampModel> getCampForUser(int userID, String status) throws BadRequestException;

	/**
	 * Returns all the volunteers associated to a particular camp @pram campID
	 * 
	 * @param campID
	 * @return
	 * @throws BadRequestException
	 */
	public List<VolunteerModel> getByCampID(int campID) throws BadRequestException;

	/***
	 * Updates a record with details as @param volunteer.
	 * 
	 * @param volunteer
	 * @throws BadRequestException
	 */
	public void update(VolunteerModel volunteer) throws BadRequestException;

	/**
	 * Returns all the volunteers for a camp @param campID where volunteer's
	 * status is @param status.
	 * 
	 * @param campID
	 * @param requestStatus
	 * @return
	 * @throws NotFoundException
	 */
	public List<VolunteerModel> getVolunteerForCamp(int campID, String requestStatus) throws NotFoundException;

}
