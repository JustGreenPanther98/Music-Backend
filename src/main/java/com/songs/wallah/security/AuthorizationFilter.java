package com.songs.wallah.security;
import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.songs.wallah.security.SpingConstraints.SecurityConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorizationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public AuthorizationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, jakarta.servlet.ServletException {

		String path = request.getServletPath();
		if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui") || path.equals("/swagger-ui.html")) {
			chain.doFilter(request, response);
			return;
		}

		String header = request.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
		String username = jwtUtil.extractUsername(token);

		if (username != null) {
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null,
					Collections.emptySet()));
		}

		chain.doFilter(request, response);
	}
}
