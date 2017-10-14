package com.coohomeless.repository;

import com.coohomeless.models.DonationStatusModel;
import com.strongloop.android.loopback.ModelRepository;

public class DonationStatusRepository extends ModelRepository<DonationStatusModel> {

    public DonationStatusRepository(String className) {
        super(className);
    }

}
