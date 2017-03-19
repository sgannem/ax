package com.nxp.appxplorer.repository;

import java.util.List;
import java.util.Optional;

import com.nxp.appxplorer.entity.Card;

/**
 * This interface is being used by the Services Layer to interact with DB to get
 * Card Details.
 * 
 */
public interface CardRepository extends Repository<Card> {
	
	/**
     * This method is used to get Card details from the Data Base.
     * 
     * @param cardIssuerId
     * @return List<Card>
     */
    List<Card> getCardsByCardIssuerId(long cardIssuerId);


    /**
     * To find Card Details by providing Id.
     * 
     * @param id
     * @return
     */
    Optional<Card> findById(long id);

}
