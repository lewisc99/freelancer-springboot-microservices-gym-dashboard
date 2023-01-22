//package com.lewis.msapigateway.filters;
//
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.lewis.msapigateway.config.SecurityConstants;
//import com.lewis.msapigateway.config.util.ConvertToGrantedAuthority;
//import com.lewis.msapigateway.config.util.JWTUtil;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    private  UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JWTVerificationException, IOException
//    {
//
//        String token = request.getHeader(SecurityConstants.HEADER_STRING);
//        if (token != null)
//        {
//            try {
//
//                String jwt = token.substring(7);
//                Map<String, List<String>> claims =
//                        JWTUtil.validateTokenAndRetrieveSubject(jwt);
//
//                List<String> getRoles = new ArrayList<String>();
//                List<String> getUsername = new ArrayList<String>();
//                getUsername = claims.get("email");
//                getRoles = claims.get("roles");
//
//                return new UsernamePasswordAuthenticationToken(getUsername.get(0),null,
//                        ConvertToGrantedAuthority.getRoles(getRoles));
//            }
//            catch (JWTVerificationException e)
//            {
//                e.printStackTrace();
//            }
//            return  null;
//        }
//        return null;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        String header = request.getHeader(SecurityConstants.HEADER_STRING);
//
//        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX))
//        {
//            chain.doFilter(request,response);
//            return;
//        }
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request,response);
//
//    }
//
//}
