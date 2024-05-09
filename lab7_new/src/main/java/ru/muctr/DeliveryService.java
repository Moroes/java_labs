package org.example;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class DeliveryService {
    ArrayDeque<String> awaiting_delivery = new ArrayDeque<>();

    public void update(String item){
        awaiting_delivery.add(item);
        this.deliveryProcess();
    }

    private void deliveryProcess() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(
                "Товар " + awaiting_delivery.getFirst() + " доставлен."
        );
    }
}
