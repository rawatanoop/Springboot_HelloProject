package helloworld.lifeline.service;

import java.util.List;

import helloworld.lifeline.model.DonationCampModel;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

public interface ISearchService {

	/**
	 * Returns all the donation camp records from the database
	 * 
	 * @return
	 * @throws BadRequestException
	 * @throws NotFoundException
	 */
	public List<DonationCampModel> getAllDonationCamps() throws BadRequestException, NotFoundException;

	/***
	 * Returns all the donation camp in a particular area.
	 * 
	 * @param area
	 * @return
	 * @throws BadRequestException
	 * @throws NotFoundException
	 */
	public List<DonationCampModel> getDonationCampByArea(String area) throws BadRequestException, NotFoundException;

	/**
	 * 
	 * @param address
	 * @return
	 * @throws BadRequestException
	 */
	public List<DonationCampModel> getDonationCampNearBy(String address) throws BadRequestException;

}
