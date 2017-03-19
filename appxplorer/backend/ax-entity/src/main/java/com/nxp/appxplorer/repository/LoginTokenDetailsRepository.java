/**
 * 
 */
package com.nxp.appxplorer.repository;

import java.util.Optional;

import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.entity.LoginTokenDetails;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Login Token Details.
 * 
 * @author nxa30710
 *
 */
public interface LoginTokenDetailsRepository extends Repository<LoginTokenDetails> {

    /**
     * This method is used to get login token details from the Data base.
     * 
     * @param bearerToken
     * @return
     */
    Optional<LoginTokenDetails> getLoginTokenDetails(String bearerToken);

    /**
     * This method is used to check whether the given bearer token exists in the
     * data base or not.
     * 
     * @param bearerToken
     * @return
     */
    boolean isBearerTokeAlreadyExists(String bearerToken);

    /**
     * This method is used to get Login Details for a given bearer token.
     * 
     * @param bearerToken
     * @return
     */
    Optional<LoginDetails> getLoginDetails(String bearerToken);

}
