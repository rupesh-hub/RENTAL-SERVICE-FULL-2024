package com.rentme.app.event.producer;

public interface EventProducer<T> {

    void send(T data);

}
