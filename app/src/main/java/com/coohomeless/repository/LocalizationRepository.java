package com.coohomeless.repository;

import com.coohomeless.models.localization.LocalizationModel;
import com.strongloop.android.loopback.ModelRepository;

public class LocalizationRepository extends ModelRepository<LocalizationModel> {

    public LocalizationRepository() {
        super("localization", "localization", LocalizationModel.class);
    }
}
