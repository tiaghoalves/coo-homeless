package com.coohomeless.repository;

import com.coohomeless.models.contributor.ContributorModel;
import com.strongloop.android.loopback.ModelRepository;

public class ContributorRepository extends ModelRepository<ContributorModel> {

    public ContributorRepository() {
        super("contributor", "contributors", ContributorModel.class);
    }
}
