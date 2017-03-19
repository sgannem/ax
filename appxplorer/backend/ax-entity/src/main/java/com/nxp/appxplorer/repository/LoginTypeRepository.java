/**
 * 
 */
package com.nxp.appxplorer.repository;

import java.util.List;

import com.nxp.appxplorer.entity.LoginType;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Login Type details.
 * 
 * @author nxa30710
 *
 */
public interface LoginTypeRepository extends Repository<LoginType> {

    /**
     * This method is used to get all the Login Types from the Data base.
     * 
     * @return
     */
    List<LoginType> getAllLoginTypes();

}
