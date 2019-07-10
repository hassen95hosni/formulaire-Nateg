package com.example.demo.interfac;

import java.util.Properties;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {
 	//@Autowired    
	//public JavaMailSender emailSender;
	 
	    public Boolean sendSimpleMessage(
	      String to, String subject, String text) throws MailException {
	    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	         
	        mailSender.setUsername("hosni.hassen9518@gmail.com");
	        mailSender.setPassword("xmkaoibnkcjvfdgx");
	         
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	        try {
	        	//System.out.println(to+subject+text);
	        	SimpleMailMessage message = new SimpleMailMessage(); 
		        message.setTo(to); 
		        message.setSubject(subject); 
		        message.setText(text);
		       // message.setFrom("hosni.hassen9518@gmail.com");
		        System.out.println(message.toString());
		        
		        mailSender.send(message);
		  return true ;	
			}

	        catch (MailAuthenticationException e) {
	        	System.out.println("this is authen error: "+e.getMessage());
	        	return false ;
			}
	        catch (MailException e) {
				System.out.println("this is mail error: "+e.getMessage());
				return false ;
				}
	        catch (Exception e) {
	        	System.out.println("this is error: "+e.getMessage());
				return false ;
			}
	        
	      
	    }
	
}
