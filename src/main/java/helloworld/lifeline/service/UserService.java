package helloworld.lifeline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import helloworld.lifeline.controller.LoginController;
import helloworld.lifeline.dao.IUserDao;
import helloworld.lifeline.entity.User;
import helloworld.lifeline.misc.Logger;
import helloworld.lifeline.misc.Utility;
import helloworld.lifeline.model.UserModel;
import inti.ws.spring.exception.client.BadRequestException;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao<User> userDao;

	private static final Logger logger = Logger.getInstance(LoginController.class);

	@Override
	public UserModel create(UserModel user) {
		logger.info("Request for creating a user record started");
		UserModel model = getModel((User) userDao.save(getEntity(user)));
		logger.info("Request for creating a user record ended successfully");
		return model;

	}

	@Override
	public UserModel getByID(int id) throws BadRequestException {
		logger.info("Request for getting a user record started");
		if (!isValidID(id))
			throw new BadRequestException(Utility.InvalidID);
		UserModel model = getModel(userDao.getById(id));
		logger.info("Request for getting a user record ended successfully");
		return model;

	}

	private boolean isValidID(int id) {
		if (id <= 0)
			return false;
		return true;
	}

	/***
	 * Utility method to convert a entity into a model
	 * 
	 * @param entity
	 * @return
	 */
	private UserModel getModel(User entity) {
		UserModel model = new UserModel();
		model.setName(entity.getName());
		model.setId(entity.getId());
		model.setEmail(entity.getEmail());
		model.setMobile(entity.getMobile());
		return model;

	}

	/****
	 * Utility method to convert a model into entity
	 * 
	 * @param model
	 * @return
	 */
	private User getEntity(UserModel model) {
		User entity = new User();
		entity.setName(model.getName());
		entity.setId(model.getId());
		entity.setEmail(model.getEmail());
		entity.setMobile(model.getMobile());
		return entity;

	}

	@Override
	public UserModel isValidUser(String email) {
		User user = userDao.getByEmail(email);
		if (user != null)
			return getModel(user);
		return null;
	}

}
