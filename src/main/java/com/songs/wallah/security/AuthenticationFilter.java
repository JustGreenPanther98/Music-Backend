package com.songs.wallah.security;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.songs.wallah.request.UserLoginRequest;
import com.songs.wallah.security.SpingConstraints.SecurityConstants;
import com.songs.wallah.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final JwtUtil jwtUtil;
	private final UserService userService;

	public AuthenticationFilter(AuthenticationManager authManager, JwtUtil jwtUtil, UserService userService) {
		setAuthenticationManager(authManager);
		setFilterProcessesUrl("/login");
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		try {

			UserLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);

			// principle => email , Credentials => password
			return getAuthenticationManager()
					.authenticate(new UsernamePasswordAuthenticationToken(creds.email(), creds.password()));

		} catch (IOException e) {
			throw new RuntimeException("Failed to parse login request", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			jakarta.servlet.FilterChain chain, Authentication auth) {

		String username = auth.getName();// username <- email
		String token = jwtUtil.generateToken(username);

		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

		response.addHeader("USER ID", userService.getUser(username).getPublicId().toString());
	}
}
