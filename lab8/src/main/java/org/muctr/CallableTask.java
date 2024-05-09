package org.example2;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTask implements Callable<Integer> {
    private final int start;
    private final int end;

    public CallableTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception{
        return  1;
    }

    public static Future<Integer> calculateAsync(int start, int end) throws Exception {

        CallableTask callableTask = new CallableTask(start, end);



        // объект future будет представлять результат вычислений задачи fibonacci

        FutureTask<Integer> future = new FutureTask<>(callableTask);



        // т.к. класс FutureTask реализует и интерфейс Future и интерфейс Runnable,

        // можно передавать его экземпляры в конструктор Thread

        Thread thread = new Thread(future);

        thread.start();



        return future;

    }
}
