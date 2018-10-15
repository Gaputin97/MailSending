package com.business.mail.repository.mongo;

import com.business.mail.model.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoEmailResponseDaoImpl implements MongoEmailResponseDao {

    private final MongoTemplate mongoTemplate;

    private final String COLLECTION_NAME = "emailResponse";

    @Autowired
    public MongoEmailResponseDaoImpl(MongoTemplate mongoTemplate) {
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
    public List<EmailResponse> deleteAll() {
        return mongoTemplate.findAll(EmailResponse.class);
    }
}
