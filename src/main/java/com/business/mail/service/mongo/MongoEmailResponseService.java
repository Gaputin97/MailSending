package com.business.mail.service.mongo;

import com.business.mail.model.EmailResponse;

import java.util.List;

public interface MongoEmailResponseService {
    void save(EmailResponse emailResponse);

    EmailResponse get(EmailResponse emailResponse);

    void delete(EmailResponse emailResponse);

    List<EmailResponse> deleteAll();
}
