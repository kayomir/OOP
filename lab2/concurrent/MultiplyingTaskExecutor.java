package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.UnitFunction;

import java.util.ArrayList;

public class MultiplyingTaskExecutor {


    public static void main(String[] args) {
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(),1,1000,1000);
        ArrayList<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            threadList.add(new Thread(new MultiplyingTask(linkedListTabulatedFunction)));
        }
        for (int i = 0; i < 10; ++i){
            threadList.get(i).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(linkedListTabulatedFunction);
    }


}