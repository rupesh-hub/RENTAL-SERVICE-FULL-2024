package com.rentme.app.event;

public interface EventProducer<T> {

    void send(T data);

}
