package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.SmartMediumSize;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Smart Medium Size Details.
 * 
 */
public interface SmartMediumSizeRepository extends Repository<SmartMediumSize> {

	/**
	 * This method is used to get all the SmartMediumSizes from the Data base.
	 * 
	 * @return List<SmartMediumSize>
	 */
	List<SmartMediumSize> getAllSmartMediumSizes();

	/**
	 * To find Smart Medium Size Details by providing Id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<SmartMediumSize> findById(long id);
}
