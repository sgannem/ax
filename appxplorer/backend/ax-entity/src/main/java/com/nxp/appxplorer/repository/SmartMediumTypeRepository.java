package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.SmartMediumType;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Smart Medium Type Details.
 * 
 */

public interface SmartMediumTypeRepository extends Repository<SmartMediumType>  {

	/**
	 * This method is used to get all the Smart Medium Types from the Data
	 * base.
	 * 
	 * @return List<SmartMediumType>
	 */
	List<SmartMediumType> getAllSmartMediumTypes();
	
    /**
     * To find Smart Medium Type Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<SmartMediumType> findById(long id);
}
