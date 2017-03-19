package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.BusinessSegment;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Business Segment Details.
 * 
 */

public interface BusinessSegmentRepository extends Repository<BusinessSegment>  {
	
	/**
	 * This method is used to get all the Business Segments from the Data
	 * base.
	 * 
	 * @return List<BusinessSegment>
	 */
	List<BusinessSegment> getAllBusinessSegments();

    /**
     * To find Business Segment Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<BusinessSegment> findById(long id);

}
