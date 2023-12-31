package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {    
    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORECT_URL = "URL is icorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage (@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCategory = conn.getResponseCode() / 100;
            if (responseCodeCategory != 2 || responseCodeCategory != 3) {
                returnMessage =  SITE_IS_DOWN; 
            } else {
                returnMessage = SITE_IS_UP;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            returnMessage = INCORECT_URL;
        } catch (IOException e){
            returnMessage =  SITE_IS_DOWN;
        }
        return returnMessage;
    }

    
}
