package com.api.bookstore.services;

import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtManager {
	static final String SECRET = "secret";
	static final String TOKEN_ISSUER = "issue";
	static final String TOKEN_PARAMETER = "token";
	static final String TOKEN_SUBJECT = "sub";
	static final int TIMEOUT = 1440 * 60 * 1000;// 24 Hours
	// Sample method to construct a JWT

	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		SecretKeySpec signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);

		// if it has been specified, let's add the expiration
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	// Sample method to validate and read the JWT

	public static boolean verifyJWT(String jwt) {

		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt)
					.getBody();

			return true;
		} catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
			return false;
		}
		// This line will throw an exception if it is not a signed JWS (as expected)

	}

	public static String getIdByJwt(String jwt) {

		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(jwt)
					.getBody();

			return claims.getId();
		} catch (SignatureException | MalformedJwtException | ExpiredJwtException e) {
			return null;
		}
		// This line will throw an exception if it is not a signed JWS (as expected)

	}
}
