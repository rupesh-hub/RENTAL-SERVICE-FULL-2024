package com.rentme.app.event.consumer;

public interface EventConsumer<T> {
    
    void consume(T data);
    
}
