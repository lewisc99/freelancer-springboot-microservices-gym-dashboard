package com.lewis.msmessageemail.service.contract;

import com.lewis.msmessageemail.entities.MailModel;
import freemarker.template.TemplateException;
import javax.mail.MessagingException;
import java.io.IOException;

public interface SendingEmailService {

    void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException;

}
