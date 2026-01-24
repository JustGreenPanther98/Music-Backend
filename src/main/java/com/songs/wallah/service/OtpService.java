package com.songs.wallah.service;

import com.songs.wallah.enums.otp.OtpResend;
import com.songs.wallah.enums.otp.OtpVerification;

public interface OtpService {
	
	void sendOtp(String email);
	OtpVerification verifyOtp(String email,String otp);
	OtpResend resendOtp(String email);
}
