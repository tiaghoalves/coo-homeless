package com.coohomeless.repository;

import com.coohomeless.models.HomelessModel;
import com.strongloop.android.loopback.ModelRepository;

public class HomelessRepository extends ModelRepository<HomelessModel> {

    public HomelessRepository(String className) {

        super(className);
    }
}
