package com.kafka.mail.Consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/*Autor Jorge Andres Vera Forero*/

public class MessageConsumer {

    private KafkaConsumer<String, String> kafkaConsumer;
    private Properties properties;

    public MessageConsumer() {
        try {
            properties = new Properties();
            properties.load(new FileReader("/app/consumer.properties"));
           
           //properties.put("group.id", groupName);
            this.kafkaConsumer = new KafkaConsumer<>(properties);
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        }
    }

    public void start(String nConsumer) {
        Thread thread = new Thread(() -> {
	        while (true) {
	            try {
	                kafkaConsumer.subscribe(List.of(properties.getProperty("topic.name")));
	                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(20));
	                records.forEach(r -> {
	                    var msg = String.format(
	                            "El %s ha leido el, value %s", nConsumer, r.value()
	                    );
	                    log.info(msg);
	                });
	
	            } catch (KafkaException e) {
	                log.error(e.getMessage());
	                this.close();
	            }
	        }
        });
        thread.setName(nConsumer);
        thread.start();
    }

    public void close() {
        this.kafkaConsumer.close();;
    }

    private static MessageConsumer consumer;
    
    public static MessageConsumer getInstance() {
        return (Objects.nonNull(consumer)) ? consumer : new MessageConsumer();
    }

    private static final Logger log = LogManager.getLogger(MessageConsumer.class);
}
