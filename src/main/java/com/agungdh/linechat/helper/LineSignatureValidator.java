package com.agungdh.linechat.helper;

import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class LineSignatureValidator {
    public boolean validateSignature(String channelSecret, String xLineSignature, String body) {
        try {
            // Create HMAC-SHA256 key
            SecretKeySpec key = new SecretKeySpec(channelSecret.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);

            // Calculate the HMAC-SHA256 hash
            byte[] signatureBytes = mac.doFinal(body.getBytes());

            // Encode the hash in Base64
            String expectedSignature = Base64.getEncoder().encodeToString(signatureBytes);

            // Compare the calculated signature with the received signature
            return expectedSignature.equals(xLineSignature);
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate LINE signature", e);
        }
    }
}
