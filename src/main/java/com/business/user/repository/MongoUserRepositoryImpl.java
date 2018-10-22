package com.business.user.repository;

import com.business.user.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoUserRepositoryImpl implements MongoUserRepository{
    private static final String COLLECTION_NAME = "user_profile";

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(UserProfile userProfile) {
        mongoTemplate.save(userProfile, COLLECTION_NAME);
    }

    @Override
    public UserProfile get(UserProfile userProfile) {
        Query query = new Query(Criteria.where("_id").is(userProfile.get_id()));
        return mongoTemplate.findOne(query, UserProfile.class, COLLECTION_NAME);
    }

    @Override
    public void delete(UserProfile userProfile) {
        mongoTemplate.remove(userProfile);
    }

    @Override
    public void deleteAll() {
        Query query = new Query(Criteria.where("_id"));
        mongoTemplate.findAllAndRemove(query, UserProfile.class, COLLECTION_NAME);
    }
}
