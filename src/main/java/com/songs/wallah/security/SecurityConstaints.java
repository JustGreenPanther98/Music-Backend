package com.songs.wallah.security;

public class SecurityConstaints {
		public static final long EXPIRATION_TIME = 80000000;
		public static final long PASSWORD_EXPIRATION_TIME = 360000;
		public static final String USER_ID = "USER ID";
		public static final String TOKEN_PREFIX = "Bearer ";
		public static final String HEADER_STRING = "Authorization";
		public static final String SIGN_UP_URL = "/users/signup";
		public static final String EMAIL_VERIFICATION="/users/verify";
		public static final String LOGIN ="/login";
		public static final String RESEND_OTP ="/users/resend-otp";
}
