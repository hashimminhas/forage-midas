package com.jpmc.midascore.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;

@Component
public class DatabaseConduit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

    public void process(Transaction transaction) {

        UserRecord sender = userRepository.findById(transaction.getSenderId());
        if (sender == null) return;

        UserRecord recipient = userRepository.findById(transaction.getRecipientId());
        if (recipient == null) return;

        if (sender.getBalance() < transaction.getAmount()) return;

        sender.setBalance(sender.getBalance() - transaction.getAmount());
        recipient.setBalance(recipient.getBalance() + transaction.getAmount());

        userRepository.save(sender);
        userRepository.save(recipient);

        TransactionRecord record = new TransactionRecord(sender, recipient, transaction.getAmount());
        transactionRepository.save(record);
    }
}