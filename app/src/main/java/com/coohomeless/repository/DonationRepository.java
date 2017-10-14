package com.coohomeless.repository;

import com.coohomeless.models.DonationModel;
import com.strongloop.android.loopback.ModelRepository;

public class DonationRepository extends ModelRepository<DonationModel>{

    public DonationRepository(String className) {
        super(className);
    }
}
