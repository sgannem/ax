package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.Application;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Application Details.
 * 
 */
public interface ApplicationRepository extends Repository<Application> {

    /**
     * This method is used to get all the Applications for a given application
     * Provider Id from the Data Base.
     * 
     * @param applicationProviderId
     * @return List<Application>
     */
    List<Application> getApplicationsByApplicationId(long applicationProviderId);

    /**
     * This method is used to get all the drafted Applications for a given
     * application Provider Id from the Data Base.
     * 
     * @param applicationProviderId
     * @return List<Application>
     */
    List<Application> getDraftedApplicationsByApplicationId(long applicationProviderId);

    /**
     * To find Application Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<Application> findById(long id);

}
