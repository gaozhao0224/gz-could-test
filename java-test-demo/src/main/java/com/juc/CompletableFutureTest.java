package com.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {

        });

        new Thread().getState();
    }


}

