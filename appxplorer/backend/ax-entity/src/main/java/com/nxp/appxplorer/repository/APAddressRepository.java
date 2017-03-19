package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.APAddress;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Application Provider Address Details.
 * 
 */
public interface APAddressRepository extends Repository<APAddress> {

    /**
     * This method is used to get Application Provider Address details from the
     * Data Base.
     * 
     * @return List<APAddress>
     */
    List<APAddress> getAPAddresses(long id);

    /**
     * This method validates the Application Provider Address against the Data
     * base.
     * 
     * @param name
     * @return
     */
    boolean isAPAddressExistsByName(String name);

    /**
     * To find Application Provider Address Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<APAddress> findById(long id);

}
