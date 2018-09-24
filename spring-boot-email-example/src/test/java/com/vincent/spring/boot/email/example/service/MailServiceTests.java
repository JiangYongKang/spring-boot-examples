package com.vincent.spring.boot.email.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.UUID;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MailServiceTests {

    @Resource
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail(
                "来自 SpringBoot 的一封邮件",
                "Hello SpringBoot Mail",
                "jiangyongkang163@163.com"
        );
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        mailService.sendHtmlMail(
                "来自 SpringBoot 的一封邮件",
                "<html><body><h1>Hello SpringBoot Mail</h1></body></html>",
                "jiangyongkang163@163.com"
        );
    }

    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        mailService.sendAttachmentsMail(
                "来自 SpringBoot 的一封邮件",
                "Hello SpringBoot Mail",
                "D:\\develop\\spring-boot-examples\\spring-boot-email-example\\pom.xml",
                "jiangyongkang163@163.com"
        );
    }

    @Test
    public void sendInlineResourceMailTest() throws MessagingException {
        String resourceId = UUID.randomUUID().toString().replace("-", "");
        mailService.sendInlineResourceMail(
                "来自 SpringBoot 的一封邮件",
                "<html><body><img src='cid:" + resourceId + "'></img></body></html>",
                "D:\\develop\\spring-boot-examples\\spring-boot-email-example\\src\\test\\resources\\meizi.jpg",
                resourceId,
                "jiangyongkang163@163.com"
        );
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id", UUID.randomUUID().toString().replace("-", ""));
        String emailContent = templateEngine.process("mail-template", context);
        mailService.sendHtmlMail(
                "来自 SpringBoot 的一封邮件",
                emailContent,
                "jiangyongkang163@163.com"
        );
    }

}
