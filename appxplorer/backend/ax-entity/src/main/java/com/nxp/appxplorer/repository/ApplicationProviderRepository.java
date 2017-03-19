package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.ApplicationProvider;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Application Provider Details.
 * 
 */
public interface ApplicationProviderRepository extends Repository<ApplicationProvider> {

    /**
     * This method is used to get all application providers from the Data Base.
     * 
     * return List<ApplicationProviders>
     */
    List<ApplicationProvider> getApplicationProviders();

    /**
     * To find Application Provider by providing Id(Long).
     * 
     * @param id
     * @return
     */
    Optional<ApplicationProvider> findById(long id);

    /**
     * To find Application Provider by providing Id(String).
     * 
     * @param id
     * @return
     */
    Optional<ApplicationProvider> findById(String id);

}
