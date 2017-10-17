package com.coohomeless.repository;

import com.coohomeless.models.meetingPoint.MeetingPointModel;
import com.strongloop.android.loopback.ModelRepository;

public class MeetingPointRepository extends ModelRepository<MeetingPointModel> {

    public MeetingPointRepository() {
        super("meeting_point", "meeting_point", MeetingPointModel.class);
    }
}
