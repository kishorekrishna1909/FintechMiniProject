package com.example.CardService.UtilityClass;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptDecrypt {
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int IV_SIZE = 12;
    private static final int TAG_LENGTH_BIT = 128;
    private static final String SECRET = "1234567812345678"; // 16-byte key for AES-128

    // Encrypt
    public static String encrypt(String input) throws Exception {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv); // generate random IV

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(input.getBytes());

        byte[] encryptedWithIV = new byte[IV_SIZE + encrypted.length];
        System.arraycopy(iv, 0, encryptedWithIV, 0, IV_SIZE);
        System.arraycopy(encrypted, 0, encryptedWithIV, IV_SIZE, encrypted.length);

        return Base64.getEncoder().encodeToString(encryptedWithIV);
    }

    // Decrypt
    public static String decrypt(String base64Encrypted) throws Exception {
        byte[] encryptedIvTextBytes = Base64.getDecoder().decode(base64Encrypted);
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedIvTextBytes, 0, iv, 0, IV_SIZE);

        byte[] encryptedBytes = new byte[encryptedIvTextBytes.length - IV_SIZE];
        System.arraycopy(encryptedIvTextBytes, IV_SIZE, encryptedBytes, 0, encryptedBytes.length);

        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), "AES");
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decrypted = cipher.doFinal(encryptedBytes);

        return new String(decrypted);
    }
}