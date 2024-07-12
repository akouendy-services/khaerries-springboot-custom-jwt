package com.akouendy.khaerries.custom_jwt.configs;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseInitialization { 

@PostConstruct
public void initialization() { 
try{ 
 
FileInputStream serviceAccount =
  new FileInputStream("./pi-pims-firebase.json"); 
 
// serviceAccountKey.json file containing the key, store this json file to your resource folder

FirebaseOptions options = FirebaseOptions.builder()
    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
    .setDatabaseUrl("https://pi-pims-default-rtdb.europe-west1.firebasedatabase.app")    
    .build();
FirebaseApp.initializeApp(options);
 } catch (Exception error) { 
error.printStackTrace(); 
}
     }
 }