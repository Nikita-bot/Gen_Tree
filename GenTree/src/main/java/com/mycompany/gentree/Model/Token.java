/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gentree.Model;

import java.security.SecureRandom;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.nio.charset.StandardCharsets;

import java.util.Base64;

public class Token {
    
    public static String generateToken(String login){
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlIjoidXNlciIsImlhdCI6MTY0NjIzMDU0MCwiZXhwIjoxNjQ2MjY2NTQwfQ.7nyrH_bvAbLehl5lQfQakr6d1JtXKunyREGmuH1
        /*
        String testToken = Token.generateHash(login);
        System.out.println(testToken);
        
        //String token = login + "." + "password" + "," + "Exodus";
        */
        TokenKey tokenKey = new TokenKey();		
        Key key = tokenKey.getKey();
		
	TokenIssuer ti = new TokenIssuer(key);
        String testToken = ti.issueToken(login);		
        System.out.println(testToken);
        
        return testToken;
    }
    
    public static String checkToken(String data, String login){

        /*
        String token = Token.generateToken(login);
       
        System.out.println("login: " + login);
        System.out.println("token: " + token);
        System.out.println("data: " + data);
        if(token.equals(data)){
            return login;
        }
        
        else{
            return "Wrong token";
        }
        */
        TokenKey tokenKey = new TokenKey();
        Key key = tokenKey.getKey();
        TokenValidator tv = new TokenValidator(key);				
		try {
		  String user = tv.validate(data);		
		  System.out.println("Validating... OK: " + user);						
		}
                catch (Exception e) {
		  System.out.println("Validating... Error: " + e.getMessage());						
		}
        return "0";
    }
    
    /*
    public static String generateHash(String login){
        JSONObject header = new JSONObject();
        header.put("alg", "HS256");
        header.put("type", "JWT");
        byte[] headerBytes = Base64.getEncoder().encode(header.toJSONString().getBytes());
        System.out.println("Header encoded bytes: " + headerBytes);
        
        JSONObject data = new JSONObject();
        data.put("login", login);
        byte[] dataBytes = Base64.getEncoder().encode(data.toJSONString().getBytes());
        
        String headerString = new String(headerBytes, StandardCharsets.UTF_8);
        String dataString = new String(dataBytes, StandardCharsets.UTF_8);
        
        String signatureString = headerString + "." + dataString + "Exodus";
        byte[] signatureBytes = Base64.getEncoder().encode(signatureString.getBytes());
        String hash = headerString + "." + dataString + "." + new String(signatureBytes, StandardCharsets.UTF_8);
        return hash;
    }
    */
    
    
    public static String decodeToken(String token){
        
        byte[] dataBytes = Base64.getDecoder().decode(token.substring(token.indexOf(".") + 1, token.lastIndexOf(".")).getBytes()); 
        System.out.println("Data decoded string: " + new String(dataBytes, StandardCharsets.UTF_8));
        
        JSONObject loginJson = null;
        JSONParser parser = new JSONParser();
        try{
            loginJson = (JSONObject) parser.parse(new String(dataBytes, StandardCharsets.UTF_8));
        }
        catch(Exception e){
            
        }
        
        return loginJson.get("sub").toString();
    }
    
}

class TokenKey {
    
    private Key key;    
    	
    public TokenKey() {                
        byte[] keyBytes = new byte[256];
        keyBytes = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlIjoidXNlciIsImlhdCI6MTY0NjIzMDU0MCwiZXhwIjoxNjQ2MjY2NTQwfQ.7nyrH_bvAbLehl5lQfQakr6d1JtXKunyREGmuH1".getBytes();   
        key = new SecretKeySpec(keyBytes,"HmacSHA256");        
    }
    
    public Key getKey() {                
        return key;
    }
}

class TokenIssuer {
	
    private Key key;

    public TokenIssuer(Key lKey) {
	   key = lKey;	
	}
  
    public String issueToken(String username) {
		
        LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(600L);
        Date expirationDateTime = Date.from(
                expiryPeriod.atZone(ZoneId.systemDefault())
                        .toInstant());
        
        
        String compactJws = Jwts.builder()
                .setSubject(username)
                .claim("scope", "user")
                .signWith(SignatureAlgorithm.HS256, key)
                .setIssuedAt(new Date())
                .setExpiration(expirationDateTime)
                .compact();
		
        return compactJws;
    }
}

class TokenValidator {
    
	private Key key;
	
	public TokenValidator(Key lKey) {		
	  key = lKey;	
	}
		
	public String validate(String token) throws Exception {		
	    
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);            
            return claims.getBody().getSubject();
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {            
            throw new Exception("Invalid JWT");
        }			
	}	
}	

