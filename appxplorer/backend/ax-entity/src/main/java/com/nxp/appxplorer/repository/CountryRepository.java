package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.Country;

public interface CountryRepository extends Repository<Country> {

    /**
     * This method is used to get all Countries from the Data Base.
     * 
     * return List<Country>
     */
    List<Country> getAllCountries();

    /**
     * To find Application Provider by providing Id(short).
     * 
     * @param id
     * @return
     */
    Optional<Country> findById(short id);

}
