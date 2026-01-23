package com.songs.wallah.service.implementation;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.songs.wallah.configuration.SwaggerConfiguration;
import com.songs.wallah.entity.OtpEntity;
import com.songs.wallah.entity.UserEntity;
import com.songs.wallah.enums.otp.OtpVerification;
import com.songs.wallah.repository.OtpRepository;
import com.songs.wallah.repository.UserRepository;
import com.songs.wallah.service.OtpService;

@Service
public class OtpServiceImplementation implements OtpService {

    private final SwaggerConfiguration swaggerConfiguration;

	private EmailSenderImpl emailSenderImpl;
	private OtpRepository otpRepository;
	private UserRepository userRepository;

	public OtpServiceImplementation(EmailSenderImpl emailSenderImpl, OtpRepository otpRepository,
			UserRepository userRepository, SwaggerConfiguration swaggerConfiguration) {
		this.emailSenderImpl = emailSenderImpl;
		this.otpRepository = otpRepository;
		this.userRepository = userRepository;
		this.swaggerConfiguration = swaggerConfiguration;
	}

	@Override
	public void sendOtp(String email) {
		otpRepository.deleteOldOtps();
		if (userRepository.findByEmail(email) != null) {
			throw new RuntimeException("User already exist");
		}
		SecureRandom secureRandom = new SecureRandom();
		int otp = secureRandom.nextInt(900000);
		OtpEntity otpEntity = new OtpEntity(email, LocalDateTime.now().plusMinutes(10), otp);
		emailSenderImpl.sendOTP(email, String.valueOf(otp));
		otpRepository.save(otpEntity);

	}

	@Override
	public OtpVerification verifyOtp(String email, String givenOtp) {
		UserEntity user = userRepository.findByEmail(email);
		if(user==null) {
			return OtpVerification.INVALID_EMAIL;
		}
		List<OtpEntity> otps=otpRepository.findOtpByEmail(email);
		if(otps==null) {
			return OtpVerification.OTP_EXPIRED;
		}
		if(String.valueOf(otps.get(otps.size()-1).getOtp()).equals(givenOtp)) {
			user.setEmailVerification(true);
			userRepository.save(user);
			return OtpVerification.SUCCESS;
		}
		return OtpVerification.ERROR;
	}

}
