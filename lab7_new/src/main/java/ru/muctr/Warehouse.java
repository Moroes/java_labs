package org.example; /**
 * Склад
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Класс "Склад"
 */
public class Warehouse {
    Map<String, Integer> quantity = new HashMap<>();

    public Warehouse(){
        quantity.put("стол", 10);
        quantity.put("шкаф", 5);
        quantity.put("кресло", 5);
        quantity.put("кухня", 2);
        quantity.put("диван", 3);
        quantity.put("кровать", 2);
    }

    public void update(String item){                    //обрабаьывает запрос на покупку
        int prevValue = quantity.get(item);
        if (prevValue == 0) {
            System.out.println("Товара \"" + item + "\" нет на складе.");
        } else {
            System.out.println("Спасибо за покупку!");
            quantity.put(item, --prevValue);
        }
    }
    public void printWarehouse(){                      //распечатывает список товаров на складе
        for (var pr: quantity.entrySet()) {
            System.out.println(pr + " шт.");
        }
    }
}
