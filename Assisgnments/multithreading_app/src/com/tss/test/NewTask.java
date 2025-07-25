//package com.tss.test;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//public class NewTask {
//
//    public static void main(String[] args) {
//
//         ExecutorService service = null;
//            try {
//                List<NewTask> tasks = Arrays.asList(
//                    new NewTask(), new NewTask(), new NewTask(), new NewTask()
//                );
//
//                service = Executors.newSingleThreadExecutor();
//
//                // Future<Integer> future = service.submit(new NewTask());
//
//                int value = service.invokeAny(tasks);
//                List<Future<Integer>> futures = service.invokeAll(tasks);
//
//                for (Future<Integer> future : futures) {
//                    System.out.println(future.get());
////                    System.out.println(value);
//                }
//
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } finally {
//                if (service != null) {
//                    service.shutdown();
//                }
//            }
//    }
//
//}