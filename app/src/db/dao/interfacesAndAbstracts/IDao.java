/**
 * 
 */
package db.dao.interfacesAndAbstracts;

import java.util.List;

/**
 * @author HADANAHM
 *
 */
public interface IDao<T> {
	
	/**
	* Creates an object from record retrieved with the given id
	* @param 	id : id of the record to retrieve in db
	* @return	retrieved object or null 
	* @throws	
	*/
	public abstract T findByID(long id);
	
	/**
	* Creates a list of objects made of the records of a type/table matching provided criterias in db
	* @param 	bean : bean olding the criterias
	* @return	created list
	* @throws	
	*/
	public abstract List<T> findByCriteria(T bean);
	
	/**
	* Creates a list objects made from all records from a type/table in db
	* @return	created list
	* @throws
	*/
	public abstract List<T> findAll();
	
	/**
	* saves in db data hold by provided bean
	* @param 	bean : bean holding data
	* @return	saved bean or null
	* @throws	
	*/
	public abstract T create(T bean);
	
	/**
	* updates record in db with id matching bean.id with the data provided in  bean.
	* @param 	bean : bean holding data
	* @return	updated bean or null
	* @throws	
	*/
	public abstract T update(T bean);
	
	public abstract int delete(T bean);
	
	public abstract int delete(long id);
}
