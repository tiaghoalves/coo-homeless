package com.coohomeless.repository;

import com.coohomeless.models.OrganizationModel;
import com.strongloop.android.loopback.ModelRepository;

public class OrganizationRepository extends ModelRepository<OrganizationModel> {

    public OrganizationRepository(String className) {

        super(className);
    }
}
