//package com.api.bookstore.services;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.auth0.jwt.interfaces.JWTVerifier;
//
//public class TokenManager {
//	
//	public static String generateToken(String userName){
//		String token=null;
//		try {
//		    Algorithm algorithm = Algorithm.HMAC256("secret");
//		    token = JWT.create()
//		        .withIssuer("auth0")
//		        .sign(algorithm);
//		    
//		} catch (JWTCreationException exception){
//		    //Invalid Signing configuration / Couldn't convert Claims.
//		}
//		return token;
//	}
//	
//	public static boolean verifyToken(String token) {
//		 	try {
//		    Algorithm algorithm = Algorithm.HMAC256("secret");
//		    JWTVerifier verifier = JWT.require(algorithm)
//		        .withIssuer("auth0")
//		        .build(); //Reusable verifier instance
//		    DecodedJWT jwt = verifier.verify(token);
//		    return true;
//		} catch (JWTVerificationException exception){
//		    //Invalid signature/claims
//			return false;
//		}
//	}
//
//}
