package com.bankapp.banking_system.Service;

import java.util.List;
import java.util.Optional;

import com.bankapp.banking_system.entities.OfferandReward;

public interface OfferRewardService {
	
	OfferandReward saveOffer(OfferandReward offerReward);
    Optional<OfferandReward> getOfferById(Long id);
    List<OfferandReward> getAllOffers();
    void deleteOffer(Long id);
	
}
