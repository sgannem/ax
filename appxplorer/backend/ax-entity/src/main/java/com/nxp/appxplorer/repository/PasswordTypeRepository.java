/**
 * 
 */
package com.nxp.appxplorer.repository;

import java.util.Optional;

import com.nxp.appxplorer.entity.PasswordType;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Password Type Details.
 * 
 * @author nxa30710
 *
 */
public interface PasswordTypeRepository extends Repository<PasswordType> {

    /**
     * This method is used to get Password type for a given Id.
     * 
     * @param id
     * @return
     */
    Optional<PasswordType> getById(short id);

}
