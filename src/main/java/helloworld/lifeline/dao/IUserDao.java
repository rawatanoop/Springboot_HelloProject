package helloworld.lifeline.dao;

public interface IUserDao<U> extends IDao<U> {

	/**
	 * Returns a user record with @param email from database.
	 * 
	 * @param email
	 * @return
	 */
	U getByEmail(String email);

}
