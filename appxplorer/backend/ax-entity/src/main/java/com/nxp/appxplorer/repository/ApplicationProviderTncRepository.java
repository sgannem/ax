package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.ApplicationProviderTnc;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * ApplicationProvider Tnc Details.
 * 
 */
public interface ApplicationProviderTncRepository extends Repository<ApplicationProviderTnc> {

    /**
     * This method is used to get all ApplicationProvider Tnc details from the
     * Data Base.
     * 
     * @return List<ApplicationProviderTnc>
     */
    List<ApplicationProviderTnc> getApplicationProvidersTnc();

    /**
     * To find ApplicationProvider Tnc Details by providing Application Provider
     * Id.
     * 
     * @param id
     * @return
     */
    Optional<ApplicationProviderTnc> findById(long applicationProviderId);

}
