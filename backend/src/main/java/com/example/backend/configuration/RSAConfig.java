package com.example.backend.configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import java.util.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RSAConfig {

    private final KeyPair keyPair;

    public RSAConfig() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // Khóa 2048-bit
        this.keyPair = keyGen.generateKeyPair(); // Tạo cặp khóa RSA
    }

    // Bean trả về khóa công khai
    @Bean
    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    // Bean trả về khóa riêng
    @Bean
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    // Phương thức mã hóa dữ liệu bằng khóa công khai
    public String encrypt(PublicKey publicKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // Khởi tạo chế độ mã hóa
        byte[] encryptedData = cipher.doFinal(data.getBytes()); // Mã hóa dữ liệu
        return Base64.getEncoder().encodeToString(encryptedData); // Trả về dữ liệu mã hóa dưới dạng Base64
    }

    // Phương thức giải mã dữ liệu bằng khóa riêng
    public String decrypt(PrivateKey privateKey, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey); // Khởi tạo chế độ giải mã
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData)); // Giải mã dữ liệu
        return new String(decryptedData); // Trả về dữ liệu giải mã
    }
}
