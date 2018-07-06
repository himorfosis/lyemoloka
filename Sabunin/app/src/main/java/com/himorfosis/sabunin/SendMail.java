package com.himorfosis.sabunin;


import android.content.Context;
import android.util.Log;


import java.util.Properties;

import javax.mail.Session;
import java.io.*;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Date;

import javax.mail.*;

import javax.mail.internet.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.mail.smtp.*;

/**
 * Created by him on 4/29/2018.
 */

public class SendMail {
    public void SendSMTPMessage() throws Exception{
        String host = "smtp.mailgun.org";
        String user = "postmaster@pencillabs.mailgun.org";
        String pass = "";

        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.mailgun.org");
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtps.auth","true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, null);

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText("Event invite is attached.");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);

        BodyPart attachment = new MimeBodyPart();
        String filename = "/Users/abdinoor/Downloads/accepted-invite.ics";
//        DataSource source = new FileDataSource(filename);
//        attachment.setDataHandler(new DataHandler(source));
        attachment.setFileName(filename);
        multipart.addBodyPart(attachment);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("jack@abdinoor.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("jill@abdinoor.com"));
        message.setSubject("Join my Event");
        message.setContent(multipart);

        Transport transport = session.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
