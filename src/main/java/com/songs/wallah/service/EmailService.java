package com.songs.wallah.service;

public interface EmailService {
	void sendEmail(String to,String body,String Subject);
	void sendOTP(String to,String otp);
}
