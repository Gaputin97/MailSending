package com.business.mail.repository;

import com.business.mail.model.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoEmailResponseRepositoryImpl implements MongoEmailResponseRepository {
    private final MongoTemplate mongoTemplate;

    private final String COLLECTION_NAME = "email_response";

    @Autowired
    public MongoEmailResponseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(EmailResponse emailResponse) {
        mongoTemplate.save(emailResponse, COLLECTION_NAME);
    }

    @Override
    public EmailResponse find(EmailResponse emailResponse) {
        Query query = new Query(Criteria.where("_id").is(emailResponse.getId()));
        return mongoTemplate.findOne(query, EmailResponse.class, COLLECTION_NAME);
    }

    @Override
    public void delete(EmailResponse emailResponse) {
        mongoTemplate.remove(emailResponse);
    }

    @Override
    public void deleteAll() {
        Query query = new Query(Criteria.where("_id"));
        mongoTemplate.findAllAndRemove(query, COLLECTION_NAME);
    }
}
