package com.akouendy.khaerries.custom_jwt.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akouendy.khaerries.custom_jwt.configs.FirebaseConfig;
import com.akouendy.khaerries.custom_jwt.dto.ApiResponse;
import com.akouendy.khaerries.custom_jwt.dto.LoginRequest;
import com.google.api.client.util.Value;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import lombok.RequiredArgsConstructor;
import org.json.JSONObject;


@Service
@RequiredArgsConstructor
public class UserService {
    /* 
    private static final String SECRET_KEY = "mySuperSecretKey12345!";
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds
    private Date issueDate;
    private Date expireDate;
    */

        private final RestTemplate restTemplate;
        private final FirebaseConfig firebaseConfig;


      public ApiResponse login(LoginRequest login) {
        /* 
        issueDate = new Date(System.currentTimeMillis());
        expireDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        */
        Map<String,Object> claims = new HashMap<>();
        
        claims.put("email", login.getEmail());
        claims.put("email_verified", true);
       ApiResponse response = new ApiResponse();
       
       try {
        String uid = DigestUtils.md5Hex(login.getEmail());
        //
        String customToken =  FirebaseAuth.getInstance().createCustomToken(uid, claims);
        String idToken = exchangeCustomTokenForIdToken(customToken);
        response.setAccessToken(idToken);

    } catch (FirebaseAuthException e) {
        e.printStackTrace();
    }
        return response;
      }  

    private String exchangeCustomTokenForIdToken(String customToken) {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken?key=" + firebaseConfig.getApiKey();

        JSONObject request = new JSONObject();
        request.put("token", customToken);
        request.put("returnSecureToken", true);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject responseBody = new JSONObject(response.getBody());
            return responseBody.getString("idToken");
        } else {
            throw new RuntimeException("Failed to exchange custom token for ID token");
        }
    }
}
