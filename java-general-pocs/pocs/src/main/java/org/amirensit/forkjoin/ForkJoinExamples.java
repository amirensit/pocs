package org.amirensit.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinExamples {
    public static void main(String[] args) {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        CustomRecursiveAction customRecursiveAction = new CustomRecursiveAction("this is an input test string");
        commonPool.execute(customRecursiveAction);
        customRecursiveAction.join();

        // or
//        customRecursiveAction.fork();
//        customRecursiveAction.join();
    }
}
