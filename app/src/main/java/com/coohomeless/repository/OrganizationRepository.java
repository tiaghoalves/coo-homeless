package com.coohomeless.repository;

import com.coohomeless.models.organization.OrganizationModel;
import com.strongloop.android.loopback.ModelRepository;

public class OrganizationRepository extends ModelRepository<OrganizationModel> {

    public OrganizationRepository(String className) {

        super(className);
    }
}
