package com.business.mail.repository.dao;

import com.business.mail.model.EmailResponse;
import com.business.mail.repository.dao.EmailResponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmailResponseDaoImpl implements EmailResponseDao {

    private final MongoTemplate mongoTemplate;

    private final String COLLECTION_NAME = "emailResponse";

    @Autowired
    public EmailResponseDaoImpl(MongoTemplate mongoTemplate) {
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
