package com.business.mail.repository;

import com.business.mail.model.EmailResponse;
import com.business.user.model.UserProfile;

import java.util.List;

public interface MongoEmailResponseRepository {
      void save(EmailResponse emailResponse);

      EmailResponse find(EmailResponse emailResponse);

      void delete(EmailResponse emailResponse);

      void deleteAll();
}
