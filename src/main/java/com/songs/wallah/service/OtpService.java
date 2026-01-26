package com.songs.wallah.service;

import com.songs.wallah.enums.otp.OperationStatus;
import com.songs.wallah.enums.otp.OtpVerification;

public interface OtpService {
	
	void sendOtp(String email);
	OtpVerification verifyOtp(String email,String otp);
	OperationStatus resendOtp(String email);
}
