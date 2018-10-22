package com.business.user.repository;

import com.business.user.model.UserProfile;

public interface MongoUserRepository {
    UserProfile getById(String id);

    void save(UserProfile userProfile);

    UserProfile get(UserProfile userProfile);

    void delete(UserProfile userProfile);

    void deleteAll();
}
