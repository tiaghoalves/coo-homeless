package com.coohomeless.repository;

import com.coohomeless.models.homeless.HomelessModel;
import com.strongloop.android.loopback.ModelRepository;

public class HomelessRepository extends ModelRepository<HomelessModel> {

    public HomelessRepository() {
        super("homeless", "homeless", HomelessModel.class);
    }
}
