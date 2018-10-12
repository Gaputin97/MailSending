package com.business.mail.service.dao;

import com.business.mail.model.EmailResponse;
import com.business.mail.repository.dao.EmailResponseDao;
import com.business.mail.service.dao.EmailResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailResponseServiceImpl implements EmailResponseService {
    private final EmailResponseDao emailResponseDao;

    @Autowired
    public EmailResponseServiceImpl(EmailResponseDao emailResponseDao) {
        this.emailResponseDao = emailResponseDao;
    }

    @Override
    public void save(EmailResponse emailResponse) {
        emailResponseDao.save(emailResponse);
    }

    @Override
    public EmailResponse get(EmailResponse emailResponse) {
        return emailResponseDao.find(emailResponse);
    }

    @Override
    public void delete(EmailResponse emailResponse) {
        emailResponseDao.delete(emailResponse);
    }

    @Override
    public List<EmailResponse> deleteAll() {
        return emailResponseDao.deleteAll();
    }
}
