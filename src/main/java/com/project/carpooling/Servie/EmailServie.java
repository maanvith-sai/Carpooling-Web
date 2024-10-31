package com.project.carpooling.Servie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServie
{
  @Autowired
  public JavaMailSender mailSender;

  @Value("spring.mail.username")
  public String from;
  public void sendEmail(String to,String subject,Integer num)
  {
   SimpleMailMessage obj=new SimpleMailMessage();
   obj.setFrom(from);
   obj.setTo(to);
   obj.setSubject(subject);
   obj.setText(String.valueOf(num));
   mailSender.send(obj);
  }
}
