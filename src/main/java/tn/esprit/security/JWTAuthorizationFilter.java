package tn.esprit.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import tn.esprit.services.AppUserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {
	private AppUserService userService;

	public JWTAuthorizationFilter(AppUserService userService) {
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
			if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}

			this.validateToken(jwt);
			UserDetails userDetails = userService.loadUserByUsername(JWTUtils.extractUsername(jwt));

			//update the security context
			UsernamePasswordAuthenticationToken authenticatedUser;
			authenticatedUser = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(),null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);


			filterChain.doFilter(request, response);
		}

	}

	public Boolean validateToken(String token) {

		Assert.notNull(userService.loadUserByUsername(JWTUtils.extractUsername(token)), "User Doesn't exist");

		Assert.notNull(JWTUtils.isTokenExpired(token), "Token is Expired");

		return true;
	}

}
