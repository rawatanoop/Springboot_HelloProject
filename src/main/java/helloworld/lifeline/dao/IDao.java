package helloworld.lifeline.dao;

import java.io.Serializable;
import java.util.List;

public interface IDao<Entity> {

	/***
	 * Save a entity into the Database.
	 * 
	 * @param entity
	 * @return
	 */
	public Serializable save(Entity entity);

	/***
	 * Delete a entity from the database.
	 * 
	 * @param entity
	 */
	public void delete(Entity entity);

	/***
	 * Returns all the entities from the database.
	 * 
	 * @return
	 */
	public List<Entity> getAll();

	/***
	 * Returns a particular entity from the database
	 * 
	 * @param id
	 * @return
	 */
	public Entity getById(Integer id);

	/****
	 * updates a particular entity in the database.
	 * 
	 * @param entity
	 */
	public void update(Entity entity);

}
