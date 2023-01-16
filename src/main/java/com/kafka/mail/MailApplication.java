package com.kafka.mail;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kafka.mail.Consumer.MessageConsumer;
import com.kafka.mail.Controller.MyController;

/*Autor Jorge Andres Vera Forero*/

@SpringBootApplication
public class MailApplication {

    @Autowired
    private MyController myController;

    public static void main(String[] args) {
    	for(int i = 1; i <= 3; i++) {
    	    String consumerName = "Consumidor " + i;
    	    //Si coloco un group diferente para cada uno, todos leeran los mensajes
    	    //String groupName = "Group" + i;
    	    
    	    MessageConsumer consumer = new MessageConsumer();
    	    consumer.start(consumerName);
    	}
        SpringApplication.run(MailApplication.class, args);
        
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    public ScheduledFuture<?> scheduledTask() {
        return scheduledExecutorService().scheduleAtFixedRate(() -> myController.sendMessage(), 0, 10, TimeUnit.SECONDS);
    }
    

}
