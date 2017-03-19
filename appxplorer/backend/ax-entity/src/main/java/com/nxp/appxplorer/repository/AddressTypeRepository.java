package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.AddressType;

public interface AddressTypeRepository extends Repository<AddressType> {

    /**
     * This method is used to get all address types from the Data Base.
     * 
     * return List<AddressType>
     */
    List<AddressType> getAllAddressTypes();

    /**
     * To find Application Provider by providing Id(short).
     * 
     * @param id
     * @return
     */
    Optional<AddressType> findById(short id);

}
