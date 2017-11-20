package com.coohomeless.repository;

import com.coohomeless.models.meetingPoint.MeetingPointContributorModel;
import com.strongloop.android.loopback.ModelRepository;

public class MeetingPointContributorRepository extends ModelRepository<MeetingPointContributorModel> {

    public MeetingPointContributorRepository() {
        super("meeting_point_contributor", "meeting_point_contributors", MeetingPointContributorModel.class);
    }
}
