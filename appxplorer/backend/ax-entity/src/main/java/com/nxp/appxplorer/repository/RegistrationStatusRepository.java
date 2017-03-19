package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.RegistrationStatus;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Registration Status Details.
 *
 */
public interface RegistrationStatusRepository extends Repository<RegistrationStatus> {

	/**
	 * This method is used to get all the Registration Statuses from the Data
	 * base.
	 * 
	 * @return List<RegistrationStatus>
	 */
	List<RegistrationStatus> getAllRegistrationStatuses();

	/**
	 * To find Registration Status Details by providing Id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<RegistrationStatus> findById(long id);
}
