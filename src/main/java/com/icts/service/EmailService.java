package com.icts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class EmailService {
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")

    private String senderEmail;
    @Value("${spring.mail.receiver}")
    private String receiverEmail;
    public static final String TITLE = "A new registration form is received.";
    public static final String TEXT = "A new registration form has been received. Please see the attachment for the content.";

    public void sendEmailWithAttachment(String fileName, String filePath) {
        threadPoolExecutor.execute(() -> {
            try {
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(senderEmail);
                helper.setTo(receiverEmail);
                helper.setSubject(TITLE);
                helper.setText(TEXT);

                File file = new File(filePath + "/" + fileName);
                helper.addAttachment(fileName, file);

                javaMailSender.send(message);
                log.info("Email sent success file name {}", file);
            } catch (MessagingException e) {
                log.error("Email sent error", e);
            }
        });
    }

    @PostConstruct
    private void init() {
        threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(30), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                log.info("new mail service thread");
                return new Thread(r);
            }
        }, new ThreadPoolExecutor.AbortPolicy());
    }
}
