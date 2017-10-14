package com.coohomeless.repository;

import com.coohomeless.models.MeetingPointModel;
import com.strongloop.android.loopback.ModelRepository;

public class MeetingPointRepository extends ModelRepository<MeetingPointModel> {

    public MeetingPointRepository(String className) {

        super(className);
    }
}
