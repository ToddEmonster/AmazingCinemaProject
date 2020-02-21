package cinema.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwttoken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwttoken = requestTokenHeader.substring(7);
			
			try {
				username = jwtTokenUtil.getUserNameFromToken(jwttoken);
			} catch(IllegalArgumentException e){
				System.out.println("Unable to get a JWT token");
			} catch(ExpiredJwtException e) {
				System.out.println("Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer !");
		}
		
		// Go west...
		if (username != null && 
				SecurityContextHolder.getContext().getAuthentication() == null
			) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
				// Check for token
				if (jwtTokenUtil.validateToken(jwttoken, userDetails)) {
					UsernamePasswordAuthenticationToken upaToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					upaToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
					);
					SecurityContextHolder
						.getContext()
						.setAuthentication(upaToken);
				}
			}
			filterChain.doFilter(request, response);
		}

}
