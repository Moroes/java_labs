package ru.muctr;

import java.util.ArrayList;

public class ShopParser implements Subject {
    ArrayList<Observer> obsList = new ArrayList<>();
    private static ShopParser uniqueInstance;

    private ShopParser(){}
    public static ShopParser getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new ShopParser();
        }
        return  uniqueInstance;
    }

    @Override
    public void registerObserver(Observer o) {
        obsList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        obsList.remove(o);
    }

    @Override
    public void notifyObservers(Item item) {
        for (Observer o: obsList){
            o.update(item);
        }
    }
}
