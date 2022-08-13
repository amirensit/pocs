package fr.mytest;

import reactor.core.publisher.Flux;

public class ColdPublisherExample {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.create(fluxSink -> {
            System.out.println("created");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
        });
        Flux<Integer> map = flux.map(i -> i * 2);
        map.subscribe(System.out::println);
        map.subscribe(System.out::println);
    }
}
