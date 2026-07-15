package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.foundation.Transaction;

@Component
public class TransactionListener {

    @Autowired
    private DatabaseConduit databaseConduit;

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-consumer")
    public void listen(Transaction transaction) {
        databaseConduit.process(transaction);
    }
}