package com.coohomeless.repository;

import com.coohomeless.models.user.UserModel;
import com.strongloop.android.loopback.ModelRepository;

public class UserRepository extends ModelRepository<UserModel> {

    public UserRepository(String className) {
        super(className);
    }
}
