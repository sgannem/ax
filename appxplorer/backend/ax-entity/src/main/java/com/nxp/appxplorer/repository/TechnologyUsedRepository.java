package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.TechnologyUsed;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Technology Used Details.
 *
 */
public interface TechnologyUsedRepository extends Repository<TechnologyUsed> {

    /**
     * This method is used to get all the Registration Statuses from the Data
     * base.
     * 
     * @return List<TechnologyUsed>
     */
    List<TechnologyUsed> getAllTechnologyUsed();

    /**
     * To find Technology Used Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<TechnologyUsed> findById(long id);

}
