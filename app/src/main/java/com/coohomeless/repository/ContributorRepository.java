package com.coohomeless.repository;

import com.coohomeless.models.ContributorModel;
import com.coohomeless.models.LocalizationModel;
import com.strongloop.android.loopback.ModelRepository;

/**
 * Created by tiago on 28/09/17.
 */

public class ContributorRepository extends ModelRepository<ContributorModel> {

    public ContributorRepository(String className) {
        super("contributor", "contributors", ContributorModel.class);
    }
}
