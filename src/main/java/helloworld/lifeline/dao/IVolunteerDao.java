package helloworld.lifeline.dao;

import java.util.List;

import helloworld.lifeline.entity.DonationCampVolunteer;

public interface IVolunteerDao<V> extends IDao<V> {

	/****
	 * Returns all the volunteers registered for a particular camp @param CampID
	 * 
	 * @param campID
	 * @return
	 */
	public List<V> getAllByCampID(int campID);

	/****
	 * Returns all the details, a user is joining.
	 * 
	 * @param userID
	 * @return
	 */
	public List<V> getAllByUserID(int userID);

	/****
	 * Returns all the details, a user is joining with request status @param
	 * status.
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public List<DonationCampVolunteer> getAllByUserStatus(int id, String status);

	/****
	 * Returns all the volunteer for a camp with request status @param status.
	 * 
	 * @param campID
	 * @param status
	 * @return
	 */
	public List<DonationCampVolunteer> getVolunteerForCamp(int campID, String status);

}
