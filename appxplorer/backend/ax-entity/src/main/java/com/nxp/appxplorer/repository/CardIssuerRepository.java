package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.CardIssuer;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Card Issuer Details.
 * 
 */
public interface CardIssuerRepository extends Repository<CardIssuer> {

    /**
     * This method is used to get Card Issuer details from the Data Base.
     * 
     * @return List<CardIsser>
     */
    List<CardIssuer> getCardIssuers();

    /**
     * This method validates the Card Issuer against the Data base.
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @return
     */
    boolean isCardIssuerExistsByName(String firstName, String lastName, String email);

    /**
     * This method validates the Card Issuer against the Data base.
     * 
     * @param companyName
     * @return
     */
    boolean isCardIssuerExistsByCompanyName(String companyName);

    /**
     * To find Card Issuer Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<CardIssuer> findById(long id);

    /**
     * To find Card Issuer Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<CardIssuer> findById(String id);

}
