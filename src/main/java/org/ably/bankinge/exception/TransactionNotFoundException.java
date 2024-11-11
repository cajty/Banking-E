package org.ably.bankinge.exception;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(){
        super("TRANSACTION NOT FOUND");
    }
}
