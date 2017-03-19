/**
 * 
 */
package com.nxp.appxplorer.repository;

import java.util.Optional;

import com.nxp.appxplorer.entity.LoginDetails;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Login Details.
 * 
 * @author nxa30710
 *
 */
public interface LoginDetailsRepository extends Repository<LoginDetails> {

    /**
     * This method is used to get login details from the Data Base.
     * 
     * @param accountType
     * @param loginType
     * @param userName
     * @return LoginDetails
     */
    Optional<LoginDetails> getLoginDetails(short accountType, short loginType, String userName);

    /**
     * This method validates the login userName against the Data base.
     * 
     * @param accountType
     * @param loginType
     * @param userName
     * @return
     */
    boolean isLoginExists(short accountType, short loginType, String userName);

    /**
     * To find Login Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<LoginDetails> findByExternalUserId(String externalUserId);

    /**
     * To check whether external userId already exists in our DB.
     * 
     * @param externalUserId
     * @return
     */
    boolean isExternalUserIdExists(String externalUserId);

}
