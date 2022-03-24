package tn.esprit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAutorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, authorization");
		response.addHeader("Access-Control-Expose-Headers","Access-Control-Allow-Origin, Access-Control-Allow-Crendentials, authorization");

		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else {
			String jwt = request.getHeader(SecurityConstants.HEADER_STRING);
			if (jwt == null || !jwt.startsWith(SecurityConstants.TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}

			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityConstants.SECRET)).build();
			DecodedJWT decodedJWT = verifier.verify((jwt.substring(SecurityConstants.TOKEN_PREFIX.length())));
			String username = decodedJWT.getSubject();


			List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(r->{
				authorities.add(new SimpleGrantedAuthority(r));
			});

			UsernamePasswordAuthenticationToken authenticatedUser;
			authenticatedUser = new UsernamePasswordAuthenticationToken(username,null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			filterChain.doFilter(request, response);
		}
		
	}

}
