package com.business.mail.service.dao;

import com.business.mail.model.EmailResponse;

import java.util.List;

public interface EmailResponseService {
    void save(EmailResponse emailResponse);

    EmailResponse get(EmailResponse emailResponse);

    void delete(EmailResponse emailResponse);

    List<EmailResponse> deleteAll();
}
