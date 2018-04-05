package com.ooad.web.utils;


import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class EmailUtil {
    public static void sendEmailRegistrationLink(String email, String hash,String un) throws AddressException, MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", Constants.EMAIL_HOST);
        props.put("mail.smtp.port",Constants.EMAIL_PORT);


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Constants.EMAIL_USER_NAME, Constants.EMAIL_PASSWORD);
                    }
                });

        String link = Constants.EMAIL_REGISTRATION_SITE_LINK+"?scope=activation&userEmail="+email+"&hash="+hash;

        StringBuilder bodyText = new StringBuilder();
        bodyText.append("<div>")
                .append("  Dear "+un+"<br/><br/>")
                .append("  Thank you for registration. Your mail ("+email+") is under verification<br/>")
                .append("  Please click <a href=\""+link+"\">here</a> or open below link in browser<br/>")
                .append("  <a href=\""+link+"\">"+link+"</a>")
                .append("  <br/><br/>")
                .append("  Thanks,<br/>")
                .append("  Amazon Team")
                .append("</div>");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(Constants.EMAIL_USER_NAME));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(email));
        message.setSubject("Email Registration");
        message.setContent(bodyText.toString(), "text/html; charset=utf-8");
        Transport.send(message);
    }
    private static final Random random = new Random();
    private static final char[] symbols;
    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }
    public static String prepareRandomString(int len) {
        char[] buf = new char[len];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}
