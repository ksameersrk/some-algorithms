package me.sameerkulkarni.projects.dsad.assgin2.six_e;

import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class Six {
    static java.util.concurrent.ForkJoinPool forkJoinPool = new java.util.concurrent.ForkJoinPool();

    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 7};
        System.out.println("Correct Sum : " + forkJoinPool.invoke(new SumMaxRecursiveTask(arr, 0)));
    }
}

class SumMaxRecursiveTask extends RecursiveTask<Integer> {

    int[] arr;
    int low;
    int high;

    public SumMaxRecursiveTask(int arr[], int low) {
        this.arr = arr;
        this.low = low;
        this.high = arr.length;
    }

    @Override
    protected Integer compute() {
        SumMaxRecursiveTask next = null;
        if (low < high) {
            next = new SumMaxRecursiveTask(arr, low + 1);
            next.fork();
        }
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int j = low; j < high; j++) {
            if (arr[j] > max) max = arr[j];
            sum += max;
        }
        if (Objects.nonNull(next)) {
            try {
                sum += next.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}