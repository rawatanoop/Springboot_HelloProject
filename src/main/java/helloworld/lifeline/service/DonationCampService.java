package helloworld.lifeline.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helloworld.lifeline.controller.LoginController;
import helloworld.lifeline.dao.DonationCampDao;
import helloworld.lifeline.entity.DonationCamp;
import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.misc.Utility;
import helloworld.lifeline.model.CategoryModel;
import helloworld.lifeline.model.DonationCampModel;
import helloworld.lifeline.model.UserModel;
import helloworld.lifeline.model.VolunteerModel;
import inti.ws.spring.exception.client.BadRequestException;
import inti.ws.spring.exception.client.NotFoundException;

@Service
public class DonationCampService /* implements IDonationCampService */ {

	@Autowired
	private DonationCampDao dcDao;

	@Autowired
	private ICategoryService dcCategoryService;

	@Autowired
	private IVolunteerService dcVolunteerService;

	@Autowired
	private IUserService userService;

	private static final Logger logger = Logger.getInstance(LoginController.class);

	/****
	 * Creates a donation camp record with @param campModel in the database.
	 * 
	 * @param campModel
	 */
	public void create(DonationCampModel campModel) {
		logger.info("Request for creating a donation camp record started");
		DonationCamp camp = getEntity(campModel);
		logger.info("Request forcreating a donation camp record ended successfully");
		dcDao.save(camp);
	}

	/**
	 * Return the camp details for a particular @param id.
	 * 
	 * @param id
	 * @return
	 * @throws BadRequestException
	 * @throws ObjectNotFoundException
	 */
	public DonationCampModel getByID(int id) throws BadRequestException, ObjectNotFoundException {
		logger.info("Request for geting a donation camp record strted");
		if (isValidID(id)) {
			DonationCampModel model = getModel(dcDao.getById(id));
			logger.info("Request for geting a donation camp record ended successfully");
			return model;
		}

		else {
			logger.info("Request for geting a donation camp record failed");
			throw new BadRequestException(Utility.InvalidID);
		}

	}

	/**
	 * Returns all the camp's details available in the records.
	 * 
	 * @return
	 * @throws NotFoundException
	 */
	public List<DonationCampModel> getAll() throws NotFoundException {
		logger.info("Request for creating a donation camp record started");
		List<DonationCamp> list = dcDao.getAll();
		if (list == null) {
			logger.error("Request for creating a donation camp record failed");
			throw new NotFoundException(Utility.NotFound);
		}
		List<DonationCampModel> models = getModelList(list);
		logger.info("Request for creating a donation camp record ended successfully");
		return models;
	}

	/**
	 * Utility method which converts entities list into models.
	 * 
	 * @param entityList
	 * @return
	 */
	private List<DonationCampModel> getModelList(List<DonationCamp> entityList) {
		List<DonationCampModel> list = new ArrayList<DonationCampModel>();
		for (Iterator<DonationCamp> iterator = entityList.iterator(); iterator.hasNext();) {
			list.add(getModel(iterator.next()));

		}
		return list;

	}

	/***
	 * Returns details for all camps for @param address
	 * 
	 * @param address
	 * @return
	 * @throws NotFoundException
	 */
	public List<DonationCampModel> getByAddress(String address) throws NotFoundException {
		logger.info("Request for details for a donation camp with address started");
		List<DonationCamp> list = dcDao.getByAddress(address);
		if (list == null) {
			throw new NotFoundException(Utility.NotFound);
		}
		List<DonationCampModel> models = getModelList(dcDao.getByAddress(address));
		logger.info("Request for details for a donation camp with address ended successfully");
		return models;
	}

	/***
	 * Utility method which converts models into entities
	 * 
	 * @param entity
	 * @return
	 */
	private DonationCampModel getModel(DonationCamp entity) {
		DonationCampModel model = new DonationCampModel();
		model.setId(entity.getId());
		model.setUserID(entity.getUserID());
		model.setCampCategoryID(entity.getCampCategoryID());
		model.setAddress(entity.getAddress());
		model.setStartDate(entity.getStartDate());
		model.setEndDate(entity.getEndDate());
		model.setUnit(entity.getUnit());
		model.setUnitLeft(entity.getUnitLeft());
		return model;

	}

	/***
	 * Utility method which converts model into entity.
	 * 
	 * @param model
	 * @return
	 */
	private DonationCamp getEntity(DonationCampModel model) {
		DonationCamp entity = new DonationCamp();
		entity.setId(model.getId());
		entity.setUserID(model.getUserID());
		entity.setCampCategoryID(model.getCampCategoryID());
		entity.setStartDate(model.getStartDate());
		entity.setEndDate(model.getEndDate());
		entity.setAddress(model.getAddress());
		entity.setUnit(model.getUnit());
		entity.setUnitLeft(model.getUnitLeft());
		return entity;

	}

	/**
	 * Checks whether a id is valid so that it can be saved in the records.
	 * 
	 * @param id
	 * @return
	 */
	private boolean isValidID(int id) {
		if (id >= 0)
			return true;
		return false;

	}

	/**
	 * Returns all the camps with @param address and @param category. Category
	 * id =0 represents all categories.
	 * 
	 * @param address
	 * @param category
	 * @return
	 * @throws NotFoundException
	 */
	public List<DonationCampModel> getByAddressCategory(String address, int category) throws NotFoundException {
		if (address.equalsIgnoreCase("all") & category == 0) {
			return getModelList(dcDao.getAll());

		}
		if (address.equalsIgnoreCase("all")) {
			return getModelList(dcDao.getByCategoryID(category));

		}
		if (category == 0) {
			return getModelList(dcDao.getByAddress(address));
		}
		List<DonationCamp> list = dcDao.getByAddressCategory(address, category);
		if (list == null)
			throw new NotFoundException(Utility.NotFound);
		return getModelList(list);
	}

	/***
	 * Returns all the camps created by a particular user.
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public List<DonationCampModel> getByUserID(int id) throws NotFoundException {
		List<DonationCamp> list = dcDao.getByUserID(id);
		if (list == null)
			throw new NotFoundException(Utility.NotFound);
		return getModelList(list);

	}

	/***
	 * This method fills the category details in camps.
	 * 
	 * @param campList
	 * @return
	 */
	public List<DonationCampModel> fillCategoryName(List<DonationCampModel> campList) {
		for (DonationCampModel donationCampModel : campList) {
			CategoryModel cat = dcCategoryService.getByID(donationCampModel.getCampCategoryID());
			donationCampModel.setCategoryName(cat.getCategory());
			donationCampModel.setSubCategoryName(cat.getSubCategory());
		}
		return campList;

	}

	/***
	 * Returns the volunteer list for camp @param campID with request
	 * status @param requestStatus.
	 * 
	 * @param campID
	 * @param requestStatus
	 * @return
	 * @throws NotFoundException
	 * @throws BadRequestException
	 */
	public List<UserModel> getVolunteerForCamp(int campID, String requestStatus)
			throws NotFoundException, BadRequestException {
		List<VolunteerModel> volunteerList = dcVolunteerService.getVolunteerForCamp(campID, requestStatus);
		List<UserModel> users = new ArrayList<UserModel>();
		for (VolunteerModel volunteerModel : volunteerList) {
			UserModel user = userService.getByID(volunteerModel.getUserID());
			user.setCampID(campID);
			user.setUnitDonate(volunteerModel.getUnitDonate());
			users.add(user);
		}
		return users;

	}

	/***
	 * Updates the camp details with @param camp.
	 * 
	 * @param camp
	 */
	public void update(DonationCampModel camp) {
		dcDao.update(getEntity(camp));
	}

}
