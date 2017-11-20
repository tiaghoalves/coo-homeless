package com.coohomeless.repository;

import com.coohomeless.models.meetingPoint.MeetingPointOrganization;
import com.strongloop.android.loopback.ModelRepository;

public class MeetingPointOngRepository extends ModelRepository<MeetingPointOrganization> {

    public MeetingPointOngRepository() {
        super("meeting_point_ong", "meeting_point_ongs", MeetingPointOrganization.class);
    }
}
