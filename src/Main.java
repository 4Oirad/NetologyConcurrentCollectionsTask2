import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private final static int ARRAY_LENGTH = 1_000_000;

    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<Integer, Integer> concurrentHashMap= new ConcurrentHashMap<>();
        Map<Integer, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        int[] array = arrayGeneration();

        Runnable runnable1 = () -> {
            for (int i = 0; i < array.length / 5; i++) {
                concurrentHashMap.put(i, i);
            }
        };
        Runnable runnable2 = () -> {
            for (int i = array.length/5; i < array.length * 2 / 5; i++) {
                concurrentHashMap.put(i, i);
            }
        };
        Runnable runnable3 = () -> {
            for (int i = array.length * 2 / 5; i < array.length * 3 / 5; i++) {
                concurrentHashMap.put(i, i);
            }
        };
        Runnable runnable4 = () -> {
            for (int i = array.length * 3 / 5; i < array.length * 4 / 5; i++) {
                concurrentHashMap.put(i, i);
            }
        };
        Runnable runnable5 = () -> {
            for (int i = array.length * 4 / 5; i < array.length; i++) {
                concurrentHashMap.put(i, i);
            }
        };

        Runnable runnable6 = () -> {
            for (int i = 0; i < array.length / 5; i++) {
                synchronizedMap.put(i, i);
            }
        };
        Runnable runnable7 = () -> {
            for (int i = array.length / 5; i < array.length * 2 / 5; i++) {
                synchronizedMap.put(i, i);
            }
        };
        Runnable runnable8 = () -> {
            for (int i = array.length * 2 / 5; i < array.length * 3 / 5; i++) {
                synchronizedMap.put(i, i);
            }
        };
        Runnable runnable9 = () -> {
            for (int i = array.length * 3 / 5; i < array.length * 4 / 5; i++) {
                synchronizedMap.put(i, i);
            }
        };
        Runnable runnable10 = () -> {
            for (int i = array.length * 4 / 5; i < array.length; i++) {
                synchronizedMap.put(i, i);
            }
        };

        long start1 = System.currentTimeMillis();
        Thread thread1 = new Thread(runnable1);
        Thread thread2  = new Thread(runnable2);
        Thread thread3  = new Thread(runnable3);
        Thread thread4  = new Thread(runnable4);
        Thread thread5  = new Thread(runnable5);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        //System.out.println(concurrentHashMap);
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;

        long start2 = System.currentTimeMillis();
        Thread thread6 = new Thread(runnable6);
        Thread thread7 = new Thread(runnable7);
        Thread thread8 = new Thread(runnable8);
        Thread thread9 = new Thread(runnable9);
        Thread thread10 = new Thread(runnable10);
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();
        //System.out.println(synchronizedMap);
        long finish2 = System.currentTimeMillis();
        long elapsed2 = finish2 - start2;


        System.out.println("Время затраченное при использовании ConcurrentHashMap: " + elapsed1 + " м/с." );
        System.out.println("Время затраченное при использовании Collections.synchronizedMap : " + elapsed2 + " м/с." );
    }

    static int[] arrayGeneration() {
        int[] array = new int[ARRAY_LENGTH];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }
}
