package com.coohomeless.repository;

import com.coohomeless.models.donation.DonationModel;
import com.strongloop.android.loopback.ModelRepository;

public class DonationRepository extends ModelRepository<DonationModel>{

    public DonationRepository() {
        super("donation", "donation", DonationModel.class);
    }
}
