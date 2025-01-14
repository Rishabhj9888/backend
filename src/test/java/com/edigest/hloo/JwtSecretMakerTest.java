package com.edigest.hloo;


import io.jsonwebtoken.security.Keys;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import java.util.Base64;

import static io.jsonwebtoken.security.Keys.secretKeyFor;


//%s is format specifier for string


public class JwtSecretMakerTest {
    @Test
    public void testgenerateSecretKey(){
//        SecretKey key= Jwts.SIG.HS512.key().build();
        SecretKey key = secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

//        String encodeKey = DatatypeConverter.printHexBinary(Key.getEncoded);
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.printf("\n=[%s]\n",encodedKey);
        Assertions.assertNotNull(encodedKey);
        Assertions.assertFalse(encodedKey.isEmpty());
    }
}
