package com.business.mail.repository.mongo;

import com.business.mail.model.EmailResponse;

import java.util.List;

public interface MongoEmailResponseDao {
      void save(EmailResponse emailResponse);

      EmailResponse find(EmailResponse emailResponse);

      void delete(EmailResponse emailResponse);

      List<EmailResponse> deleteAll();
}
