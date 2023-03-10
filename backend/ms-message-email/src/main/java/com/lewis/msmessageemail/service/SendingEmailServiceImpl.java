package com.lewis.msmessageemail.service;

import com.lewis.msmessageemail.entities.MailModel;
import com.lewis.msmessageemail.service.contract.SendingEmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class SendingEmailServiceImpl  implements SendingEmailService {

    private static Logger log = LoggerFactory.getLogger(SendingEmailServiceImpl.class);


    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Override
    public void sendEmail(MailModel mailModel) throws MessagingException, IOException, TemplateException {


        //content to template
        log.info("Sending Email to: " + mailModel.getTo());
        Map model = new HashMap();
        model.put("content", mailModel.getContent());
        model.put("name", mailModel.getName());
        mailModel.setModel(model);

        //adding image to template
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mimeMessageHelper.addInline("logo.png", new ClassPathResource("classpath:/gym.webp"));

        Template template = emailConfig.getTemplate("template-email.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,mailModel);

        //adding file - image path or file
//        FileSystemResource fileSystemResource = new FileSystemResource(new File("D:\\arquivos\\programation\\workspace-java\\2023\\month-1\\1-gym-project\\backend\\ms-message-email\\src\\main\\resources\\static\\gym-corpus.JPG"));
//        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);


        //email properties
        mimeMessageHelper.setTo(mailModel.getTo());
        mimeMessageHelper.setText(html, true);
        mimeMessageHelper.setSubject(mailModel.getSubject());
        mimeMessageHelper.setFrom(mailModel.getFrom());

        emailSender.send(message);

    }
}
