package com.kafka.mail.Controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.mail.Producer.MessageProducer;

/*Autor Jorge Andres Vera Forero*/

@RestController
@RequestMapping("/messages")
public class MyController {

    @Autowired
    private MessageProducer messageProducer;

    public void sendMessage() {
        // Crear mensaje JSON
    	String email = generateRandomString() + "@test.com";
        int code = generateRandomCode();
        String message = "{\"Fecha actual\":\"" + new Date() + "\",\"Email\":\"" + email + "\",\"Mensaje\":\"Hola, tu código de acceso es " + code + "\"}";
        messageProducer.send(message);
    }

	private String generateRandomString() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 5;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit));
	        buffer.append((char) randomLimitedInt);
	    }
	    return buffer.toString();
	}
	
	private int generateRandomCode() {
	    Random random = new Random();
	    return random.nextInt(9999);
	}
	
}
