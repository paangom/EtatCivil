/**
 * 
 */
package dao;

import java.util.List;

import models.Connected;
import models.Users;

/**
 * @author admin
 *
 */
public interface ConnectedDAO {

	public boolean addConnected(Users con);
	public boolean deleteConnected(Connected con);
	public List<Connected> findAllUsers();
	public Connected findUser(Users u);
	public boolean updateConnected(Users con);
}
