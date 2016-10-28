package helloworld.lifeline.service;

import helloworld.lifeline.model.UserModel;
import inti.ws.spring.exception.client.BadRequestException;

public interface IUserService {

	/***
	 * Creates a user record in the database
	 * 
	 * @param user
	 * @return
	 */
	public UserModel create(UserModel user);

	/***
	 * Returns a user record for @param userID
	 * 
	 * @param userID
	 * @return
	 * @throws BadRequestException
	 */
	public UserModel getByID(int userID) throws BadRequestException;

	/***
	 * Checks whether a user with @param email exists in database.
	 * 
	 * @param email
	 * @return
	 */
	public UserModel isValidUser(String email);

}
