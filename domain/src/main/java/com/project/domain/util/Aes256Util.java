/**
 *
 * JWT 내부 암호화(AES-256)를 위한 유틸 클래스 (현재 미사용)
 *
 * 본 프로젝트에서는 JWT의 무결성을 검증하기 위해 서명(Signature) 방식을 사용하며,
 * 추가적인 암호화(AES-256)는 적용하지 않음.
 *
 * JWT는 기본적으로 서명(Signature)을 통해 변조를 방지할 수 있으며,
 * HTTPS 환경에서 안전하게 전송되므로 추가적인 암호화 없이도 충분한 보안성을 제공함.
 *
 * 만약 향후 JWT 페이로드에 민감한 정보(예: 개인정보, 금융 정보 등)를 포함해야 한다면,
 * 이 클래스를 활용하여 JWT Payload를 AES-256으로 암호화할 수 있음.
 *
 * 현재는 성능 최적화 및 필요성에 대한 고려로 인해 해당 클래스를 사용하지 않으며,
 * 필요 시 활성화하여 적용 가능함.
 *
package com.project.domain.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class Aes256Util {

    public static String algorithm = "AES/CBC/PKCS5Padding";
    // 키길이 16, 24, 32 Byte 지원  ->  AES 가 128/192/256 비트키를 지원
    private static final String KEY = "ECOMMERCEKEYISECOMMERCEKEY123456";
    private static final String IV = KEY.substring(0, 16);

    public static String encrypt(String text) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            return null;
        }
    }

    public static String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return null;
        }
    }
}
*/
