package com.edigest.hloo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import java.util.Base64;

import static org.yaml.snakeyaml.tokens.Token.ID.Key;

//import javax.crypto.SecretKey;

//import static org.yaml.snakeyaml.tokens.Token.ID.Key;

//import static org.yaml.snakeyaml.tokens.Token.ID.Key;

public class JwtSecretMakerTest {
    @Test
    public void generateSecretKey(){
//        SecretKey key= Jwts.SIG.HS512.key().build();
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

//        String encodeKey = DatatypeConverter.printHexBinary(Key.getEncoded);
        String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.printf("\n=[%s]\n",encodedKey);
    }
}
