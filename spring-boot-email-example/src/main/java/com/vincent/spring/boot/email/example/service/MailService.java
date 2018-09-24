package com.vincent.spring.boot.email.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送简单邮件
     * @param subject 主题
     * @param content 内容
     * @param to      收件人邮箱
     */
    public void sendSimpleMail(String subject, String content, String... to) {
        System.out.println(Thread.currentThread().getId() + "  1");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送 Html 邮件
     * @param subject 主题
     * @param content 内容
     * @param to      收件人邮箱
     * @throws MessagingException
     */
    public void sendHtmlMail(String subject, String content, String... to) throws MessagingException {
        System.out.println(Thread.currentThread().getId() + "  2");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带有附件的邮件
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件路径
     * @param to       收件人邮箱
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String subject, String content, String filePath, String... to) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        mailSender.send(mimeMessage);
    }

    /**
     * 发送带有图片的邮件
     * @param subject      主题
     * @param content      内容
     * @param resourcePath 资源路径
     * @param resourceId   资源ID
     * @param to           收件人邮箱
     * @throws MessagingException
     */
    public void sendInlineResourceMail(String subject, String content, String resourcePath, String resourceId, String... to) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        FileSystemResource fileSystemResource = new FileSystemResource(new File(resourcePath));
        mimeMessageHelper.addInline(resourceId, fileSystemResource);
        mailSender.send(mimeMessage);
    }

}
