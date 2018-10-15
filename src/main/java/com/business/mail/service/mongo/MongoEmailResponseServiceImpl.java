package com.business.mail.service.mongo;

import com.business.mail.model.EmailResponse;
import com.business.mail.repository.mongo.MongoEmailResponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoEmailResponseServiceImpl implements MongoEmailResponseService {
    private final MongoEmailResponseDao mongoEmailResponseDao;

    @Autowired
    public MongoEmailResponseServiceImpl(MongoEmailResponseDao mongoEmailResponseDao) {
        this.mongoEmailResponseDao = mongoEmailResponseDao;
    }

    @Override
    public void save(EmailResponse emailResponse) {
        mongoEmailResponseDao.save(emailResponse);
    }

    @Override
    public EmailResponse get(EmailResponse emailResponse) {
        return mongoEmailResponseDao.find(emailResponse);
    }

    @Override
    public void delete(EmailResponse emailResponse) {
        mongoEmailResponseDao.delete(emailResponse);
    }

    @Override
    public List<EmailResponse> deleteAll() {
        return mongoEmailResponseDao.deleteAll();
    }
}
