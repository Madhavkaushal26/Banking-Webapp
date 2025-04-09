package com.bankapp.banking_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapp.banking_system.entities.OfferandReward;

@Repository
public interface OfferandRewardRepo extends JpaRepository<OfferandReward, Long> {

	Optional<OfferandReward> findByOfferCode(String offerCode);
	
}
