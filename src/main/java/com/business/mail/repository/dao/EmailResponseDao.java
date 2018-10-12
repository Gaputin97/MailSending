package com.business.mail.repository.dao;

import com.business.mail.model.EmailResponse;

import java.util.List;

public interface EmailResponseDao {
      void save(EmailResponse emailResponse);

      EmailResponse find(EmailResponse emailResponse);

      void delete(EmailResponse emailResponse);

      List<EmailResponse> deleteAll();
}
