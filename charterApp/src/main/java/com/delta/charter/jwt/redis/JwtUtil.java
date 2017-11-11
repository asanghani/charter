package com.delta.charter.jwt.redis;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String REDIS_SET_ACTIVE_SUBJECTS = "active-subjects";
    
    @Value("${app.name}")
    private String APP_NAME;

    @Value("${jwt.secret}")
    public String SECRET;
    
    @Value("${jwt.signature_algorithm}")
    public String SIGNATURE_ALGORITHM;

    @Value("${jwt.expiration}")
    private int EXPIRATION;

    @Value("${jwt.mobile_expires_in}")
    private int MOBILE_EXPIRES_IN;

    @Value("${jwt.header}")
    private String AUTH_HEADER;

    static final String AUDIENCE_UNKNOWN = "unknown";
    static final String AUDIENCE_WEB = "web";
    static final String AUDIENCE_MOBILE = "mobile";
    static final String AUDIENCE_TABLET = "tablet";

    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);
        String token = builder.compact();
        RedisUtil.INSTANCE.sadd(subject,token);
        return token;
    }

    public static String parseToken(HttpServletRequest httpServletRequest, String token, String signingKey){
       // String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
    //	 token = null;
        if(token == null) {
            return null;
        }

        String subject = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        if (!RedisUtil.INSTANCE.sismember(subject,token)) {
            return null;
        }

        return subject;
    }

    public static void invalidateRelatedTokens(HttpServletRequest httpServletRequest, String token, String signingKey) {
        RedisUtil.INSTANCE.srem(parseToken(httpServletRequest,token,signingKey),gettoken(httpServletRequest));
    }
    public static String getrequestusernamefromheader(HttpServletRequest request){
    
    String authhead = request.getHeader("Authorization");
    if (authhead != null) {
    	byte [] e = Base64.getDecoder().decode(authhead.substring(6)); 
        String usernpass = new String(e);
        String user = usernpass.substring(0, usernpass.indexOf(":"));
        String password = usernpass.substring(usernpass.indexOf(":") + 1);
        return user;
     }
    else{
    	return null;
    	//Put some condition
    }   
    }
    
    public static String gettoken(HttpServletRequest request){
    	return request .getHeader("x-auth-token");
    }
}

