package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.CardIssuerTnc;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Card Issuer Tnc Details.
 * 
 */
public interface CardIssuerTncRepository extends Repository<CardIssuerTnc> {

    /**
     * This method is used to get all CardIssuers Tnc details from the Data
     * Base.
     * 
     * @return List<CardIssuerTnc>
     */
    List<CardIssuerTnc> getCardIssuersTnc();

    /**
     * To find CardIssuer Tnc Details by providing card issuer Id.
     * 
     * @param id
     * @return
     */
    Optional<CardIssuerTnc> findById(long cardIssuerId);

}
