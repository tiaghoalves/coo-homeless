package com.coohomeless.repository;

import com.coohomeless.models.homeless.HomelessInfoModel;
import com.strongloop.android.loopback.ModelRepository;


public class HomelessInfoRepository extends ModelRepository<HomelessInfoModel> {

    public HomelessInfoRepository(){
        super("homeless_info", "homeless_infos", HomelessInfoModel.class);
    }
}
