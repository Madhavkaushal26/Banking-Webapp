package com.bankapp.banking_system.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bankapp.banking_system.Repository.OfferandRewardRepo;
import com.bankapp.banking_system.Service.OfferRewardService;
import com.bankapp.banking_system.entities.OfferandReward;


@Service 
public class OfferRewardServImpl implements OfferRewardService {
	
	@Autowired
	private OfferandRewardRepo offerrewardRepo;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public OfferandReward saveOffer(OfferandReward offerReward) {
		
		return offerrewardRepo.save(offerReward);
	}

	@Override
	public Optional<OfferandReward> getOfferById(Long id) {
		
		return offerrewardRepo.findById(id);
	}

	@Override
	public List<OfferandReward> getAllOffers() {
		
		return offerrewardRepo.findAll();
	}

	@Override
	public void deleteOffer(Long id) {
		
		offerrewardRepo.deleteById(id);

	}
	public Map<Long, List<OfferandReward>> evaluateEligibleOffers() {
        Map<Long, List<OfferandReward>> customerOfferMap = new HashMap<>();
        List<OfferandReward> offers = offerrewardRepo.findAll();

        for (OfferandReward offer : offers) {
            String query = offer.getOfferQuery();
            try {
                List<Long> customerIds = jdbcTemplate.queryForList(query, Long.class);

                for (Long custId : customerIds) {
                    customerOfferMap
                        .computeIfAbsent(custId, k -> new ArrayList<>())
                        .add(offer);
                }
            } catch (Exception e) {
                System.out.println("Error running query for offer ID " + offer.getId() + ": " + e.getMessage());
            }
        }

        return customerOfferMap;
    }
	

}
