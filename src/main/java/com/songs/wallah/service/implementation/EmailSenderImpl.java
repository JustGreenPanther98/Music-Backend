package com.songs.wallah.service.implementation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.songs.wallah.service.EmailService;

@Service
public class EmailSenderImpl implements EmailService {
	
	private JavaMailSender javaMailSender;
	
	public EmailSenderImpl(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}

	@Value("${spring.mail.username}")
	private String from;
	
	@Override
	public void sendEmail(String to, String Subject, String body) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(Subject);
		simpleMailMessage.setText(body);
		javaMailSender.send(simpleMailMessage);
	}

	@Override
	public void sendOTP(String to, String otp) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); 
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject("Email verification");
		simpleMailMessage.setText(otp);
		javaMailSender.send(simpleMailMessage);
	}
}
