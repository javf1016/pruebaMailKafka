package com.kafka.mail.Producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/*Autor Jorge Andres Vera Forero*/

@Service
public class MessageProducer {

    private static MessageProducer producer;
    private KafkaProducer<String, String> kafkaProducer;
    private Properties properties;

    private MessageProducer() {
        try {
            properties = new Properties();
            properties.load(new FileReader("/app/application-docker.properties"));
            kafkaProducer = new KafkaProducer<>(properties);
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
        }
    }

    public void send(String message) {
        try {
            var record = new ProducerRecord<String, String>(properties.getProperty("topic.name"), message);
            this.kafkaProducer.send(record);
        } catch (KafkaException e) {
            log.error(e.getMessage());
            this.close();
        }
    }

    public void close() {
        this.kafkaProducer.close();
    }

    public static MessageProducer getInstance() {
        return (Objects.nonNull(producer)) ? producer : new MessageProducer();
    }

    private static final Logger log = LogManager.getLogger(MessageProducer.class);
}
