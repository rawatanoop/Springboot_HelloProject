package helloworld.lifeline.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import helloworld.lifeline.controller.LoginController;
import helloworld.lifeline.dao.IVolunteerDao;
import helloworld.lifeline.entity.DonationCampVolunteer;
import helloworld.lifeline.entity.DonationCampVolunteerPK;
import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.misc.Utility;
import helloworld.lifeline.model.CategoryModel;
import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.VolunteerModel;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@Service
public class VolunteerService implements IVolunteerService {

	@Autowired
	private IVolunteerDao<DonationCampVolunteer> dcVolunteerDao;

	@Autowired
	private ICategoryService dcCategoryService;

	@Autowired
	private DonationCampService dcService;

	private static final Logger logger = Logger.getInstance(LoginController.class);

	@Override
	public void create(VolunteerModel volunteer) throws DataIntegrityViolationException {

		dcVolunteerDao.save(getEntity(volunteer));
	}

	@Override
	public List<DonationCampModel> getCampForUser(int id, String status) throws BadRequestException {
		logger.info("Request for getting all camps for user with particular status started");
		if (!isVAalidID(id))
			throw new BadRequestException(Utility.InvalidID);
		List<VolunteerModel> list = getModelList(dcVolunteerDao.getAllByUserStatus(id, status));
		ArrayList<DonationCampModel> campList = new ArrayList<DonationCampModel>();
		for (VolunteerModel volunteerModel : list) {
			DonationCampModel camp = dcService.getByID(volunteerModel.getCampID());
			camp.setUnitDonate(volunteerModel.getUnitDonate());
			campList.add(camp);
		}
		List<DonationCampModel> camps = fillCategoryName(campList);
		logger.info("Request for getting all camps for user with particular status ended successfully");
		return camps;

	}

	/***
	 * Utility method to fill category details into camps
	 * 
	 * @param campList
	 * @return
	 */
	private List<DonationCampModel> fillCategoryName(List<DonationCampModel> campList) {
		for (DonationCampModel donationCampModel : campList) {
			CategoryModel cat = dcCategoryService.getByID(donationCampModel.getCampCategoryID());
			donationCampModel.setCategoryName(cat.getCategory());
			donationCampModel.setSubCategoryName(cat.getSubCategory());
		}
		return campList;

	}

	@Override
	public List<VolunteerModel> getByCampID(int id) throws BadRequestException {
		if (!isVAalidID(id))
			throw new BadRequestException(Utility.InvalidID);
		return getModelList(dcVolunteerDao.getAllByCampID(id));
	}

	@Override
	public void update(VolunteerModel volunteer) throws BadRequestException {
		if (volunteer.getRequestStatus().equalsIgnoreCase("Accept")) {
			DonationCampModel camp = dcService.getByID(volunteer.getCampID());
			camp.setUnitLeft(camp.getUnit() - volunteer.getUnitDonate());
			dcService.update(camp);
			dcVolunteerDao.update(getEntity(volunteer));
		} else if (volunteer.getRequestStatus().equalsIgnoreCase("Reject")) {
			dcVolunteerDao.update(getEntity(volunteer));
		} else
			throw new BadRequestException("Voulteer status is not correct");

	}

	/**
	 * Utility method to convert a entity into model.
	 * 
	 * @param entity
	 * @return
	 */
	private VolunteerModel getModel(DonationCampVolunteer entity) {
		VolunteerModel model = new VolunteerModel();
		model.setUserID(entity.getDonationCampVolenteerPK().getUserID());
		model.setCampID(entity.getDonationCampVolenteerPK().getCampID());
		model.setRequestStatus(entity.getRequestStatus());
		model.setUnitDonate(entity.getUnitDonate());
		return model;

	}

	@Override
	public List<VolunteerModel> getVolunteerForCamp(int campID, String status) throws NotFoundException {
		List<DonationCampVolunteer> list = dcVolunteerDao.getVolunteerForCamp(campID, status);
		if (list == null|list.size()==0)
			throw new NotFoundException(Utility.NotFound);

		return getModelList(list);

	}

	/***
	 * Utility method to convert model into entity
	 * 
	 * @param model
	 * @return
	 */
	private DonationCampVolunteer getEntity(VolunteerModel model) {
		DonationCampVolunteer entity = new DonationCampVolunteer(
				new DonationCampVolunteerPK(model.getUserID(), model.getCampID()));
		entity.setRequestStatus(model.getRequestStatus());
		return entity;
	}

	/***
	 * Utility method to convert entities into models.
	 * 
	 * @param entityList
	 * @return
	 */
	private List<VolunteerModel> getModelList(List<DonationCampVolunteer> entityList) {
		List<VolunteerModel> list = new ArrayList<VolunteerModel>();
		for (Iterator<DonationCampVolunteer> iterator = entityList.iterator(); iterator.hasNext();) {
			list.add(getModel(iterator.next()));

		}
		return list;

	}

	/**
	 * Checks whether a id is valid or not.
	 * 
	 * @param id
	 * @return
	 */
	private boolean isVAalidID(int id) {
		if (id > 0)
			return true;
		else
			return false;
	}
}
