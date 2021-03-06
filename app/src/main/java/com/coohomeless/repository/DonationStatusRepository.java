package com.coohomeless.repository;

import com.coohomeless.models.donation.DonationStatusModel;
import com.strongloop.android.loopback.ModelRepository;

public class DonationStatusRepository extends ModelRepository<DonationStatusModel> {

    public DonationStatusRepository() {
        super("donation_status", "donation_statuses", DonationStatusModel.class);
    }

}
