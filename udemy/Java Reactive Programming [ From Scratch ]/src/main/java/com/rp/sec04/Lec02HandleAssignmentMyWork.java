package com.rp.sec04;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignmentMyWork {

    public static void main(String[] args) {
        Flux<Integer> range = Flux.range(1, 10);
        Flux<Integer> secondRange = range.map(i -> i * 10);
        range.subscribe(System.out::println);
        System.out.println("------");
        secondRange.subscribe(System.out::println);
    }
}
