package ru.mucrt;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        List<Fridge> fridges = FirdgeReader.readFile("src/main/resources/fridges.txt");

        System.out.println("\nСортировка по названию");
        fridges.stream().sorted(Comparator.comparing(Fridge::getName)).forEach(System.out::println);

        System.out.println("\nСортировка по цене");
        fridges.stream().sorted(Comparator.comparing(Fridge::getPrice)).forEach(System.out::println);

        System.out.println("\nХолодильник с максимальным рейтингом");
        System.out.println(fridges.stream().max(Comparator.comparing(Fridge::getRating)));

        System.out.println("\nХолодильники с ценой от 20_000 до 30_000");
        fridges.stream().filter(x -> x.getPrice() > 20_000 & x.getPrice() < 30_000).forEach(System.out::println);

        System.out.println("\nСредний рейтинг");
        System.out.println(fridges.stream().mapToDouble(Fridge::getRating).average().orElse(0));

        System.out.println("\nКоличество белых холодильников");
        System.out.println(fridges.stream().filter(x -> Objects.equals(x.getColor(), "белый")).count());

        System.out.println("\nУ всех ли холодильников оценка покупателей больше 4.");
        System.out.println(fridges.stream().allMatch(x -> x.getRating() > 4));

        System.out.println("\nЕсть ли холодильник стоимостью более 50 тыс. руб");
        System.out.println(fridges.stream().anyMatch(x -> x.getPrice() > 50_000));

        System.out.println("\nCамый дорогой холодильник");
        System.out.println(fridges.stream().collect(Collectors.maxBy(Comparator.comparing(Fridge::getPrice))));

        System.out.println("\nРазделите все холодильники на 2 коллекции: с оценкой больше 4,5 и меньше 4,5");
        Map <Boolean, List<Fridge>> partByRate = fridges.stream()
                .collect(Collectors.partitioningBy(x -> x.getRating() > 4.5));

        System.out.println("Оценки больше 4.5");
        for(Map.Entry<Boolean, List<Fridge>> item : partByRate.entrySet()){

            System.out.println(item.getKey());
            for(Fridge fridge : item.getValue()){

                System.out.println(fridge);
            }
            System.out.println();
        }

        Map<String, List<Fridge>> fridgeByBrand = fridges.stream().collect(
                groupingBy(Fridge::getBrand));
        System.out.println("Группировка холодильников по бренду");
        for(Map.Entry<String, List<Fridge>> item : fridgeByBrand.entrySet()){
            System.out.println(item);
        }

        Map<String, Optional<Double>> countFridgesAndMaxPriceByBrand = fridges
                .stream()
                .collect(groupingBy(x -> x.getBrand() + " count: " + fridgeByBrand.get(x.getBrand()).size(),
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Fridge::getPrice)),
                                maxPrice -> maxPrice.map(Fridge::getPrice))
                        )
                );
        System.out.println("\nКоличество холодильников и максимальная цена по брендам");
        for(Map.Entry<String, Optional<Double>> item : countFridgesAndMaxPriceByBrand.entrySet()){
            System.out.println(item.getKey() + ", максимальная цена: " + item.getValue().orElse(0.0));
        }

        String silverFridges = fridges.stream()
                .filter(x -> Objects.equals(x.getColor(), "серебристый"))
                .map(Fridge::getName)
                .collect(Collectors.joining(",", "Модели холодильников серебристого цвета:", "."));
        System.out.println("\n" + silverFridges);
    }
}
