package ru.muctr;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

public class DeliveryService implements Observer {
    ArrayDeque<String> awaiting_delivery = new ArrayDeque<>();
    public DeliveryService(){
        ShopParser.getInstance().registerObserver(this);
    }

    public void update(Item item){
        awaiting_delivery.add(item.getName());
        this.deliveryProcess();
    }

    private void deliveryProcess() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "Товар " + awaiting_delivery.getFirst() + " доставлен."
        );
    }
}
