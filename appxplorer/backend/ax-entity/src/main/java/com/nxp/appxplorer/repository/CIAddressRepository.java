package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.CIAddress;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Card Issuer Address Details.
 * 
 */
public interface CIAddressRepository extends Repository<CIAddress> {

    /**
     * This method is used to get Card Issuer Address details from the Data
     * Base.
     * 
     * @return List<CIAddress>
     */
    List<CIAddress> getCIAddresses(long id);

    /**
     * This method validates the Card Issuer Address against the Data base.
     * 
     * @param name
     * @return
     */
    boolean isCIAddressExistsByName(String name);

    /**
     * To find Card Issuer Address Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<CIAddress> findById(long id);

}
