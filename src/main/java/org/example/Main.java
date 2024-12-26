package org.example;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String clientId = "009B3F7512D8D19B63034DD52861BB41";
        String timestamp = "1732531106895";
        String encryptData = "FAv0iuSRpSYQ3dcTijGsF2lRK99bDFTNqHH7Ka/JVuVc6 WYOXdB9nYKMJgfa8kXB5bJEw4CLnMKDWvaX6oZlA==";
        String secretKey = "009B3F7512D8D19B63034DD52861BB41";
        var result = hmacEncode(buildRawSignature(clientId, String.valueOf(timestamp), encryptData), secretKey);
        System.out.println(result); //43957674011d1d9896d73998d66a55dd66863c415b4074d83b2014285df63853
    }
    public static String hmacEncode(String data, String key) {
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256HMAC.init(secretKey);
            return Hex.encodeHexString(sha256HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception var4) {
            Exception e = var4;
            return null;
        }
    }

    public static String buildRawSignature(String clientId, String timestamp, String data) {
        return String.format("%s|%s|%s", clientId, timestamp, data);
    }

}