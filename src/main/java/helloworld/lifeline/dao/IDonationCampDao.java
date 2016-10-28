package helloworld.lifeline.dao;

import java.util.List;

public interface IDonationCampDao<DC> extends IDao<DC> {

	/***
	 * Returns all the camp registered in a particular area.
	 * 
	 * @param address
	 * @return
	 */
	public List<DC> getByAddress(String address);
}
