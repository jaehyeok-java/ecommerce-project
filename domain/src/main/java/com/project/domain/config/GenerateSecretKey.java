/**
 *
 * BASE64로 인코딩된 secretKey 생성기 코드
 *
package com.project.domain.config;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class GenerateSecretKey {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Base64 Secret Key: " + base64Key);
    }
}
*/
