package org.example;

/**
 * Класс "Бухгалтерия"
 */
public class Accounting {
    private static Accounting uniqueInstance;
    private int income = 0;
    private final String PASSWORD = "admin";

    private Accounting(){}
    public static Accounting getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Accounting();
        }
        return  uniqueInstance;
    }
    /**
     * Метод распечатывает доход по паролю
     * @param psw - пароль
     */
    public void getIncome(String psw){
        if (psw.equals(PASSWORD)) {System.out.println("Your income is " + income);}
        else {
            System.out.println("Неверный пароль");
        }
    }

    /**
     * Метод считает общий доход
     * @param newOrder - сумма нового заказа
     */
    public void update(int newOrder){
        income += newOrder;
    }
}
