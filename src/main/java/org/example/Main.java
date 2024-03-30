package org.example;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Long> sequentialTime = new ArrayList<>();
        List<Long> parallelTime = new ArrayList<>();
        Long sequentialTimeSum = 0l;
        Long parallelTimeSum = 0l;

        int listSize = 100000;
        int minElement = 1;
        int maxElement = listSize;
        int threadNum = 8;
        int depth = 3;

        for(int i=0; i<25; i++){
            List<Integer> list1 = ListUtil.generateList(listSize, minElement, maxElement);
            List<Integer> list2 = ListUtil.copyList(list1);

            //SequentialSort
            long startTime = System.currentTimeMillis();
            GnomeSort.sequentialGnomeSort(list1, 0, list1.size());
            long endTime = System.currentTimeMillis();
            if(!ListUtil.isSorted(list1)) throw new Exception("Unsorted sequential list");
            long elapsedTime = endTime - startTime;
            if(i>5) {
                sequentialTime.add(elapsedTime);
                sequentialTimeSum += elapsedTime;
            }
            //ParallelSort
            if(ListUtil.isSorted(list2)) throw new Exception("Sorted parallel list");
            long startTime2 = System.currentTimeMillis();
            list2 = GnomeSort.parallelGnomeSort(list2, minElement, maxElement, threadNum, depth);
            long endTime2 = System.currentTimeMillis();
            if(!ListUtil.isSorted(list2)) throw new Exception("Unsorted parallel list");
            long elapsedTime2 = endTime2 - startTime2;
            if(i>5){
            parallelTime.add(elapsedTime2);
            parallelTimeSum += elapsedTime2;
            }

            if(!ListUtil.listEqual(list1, list2)) throw new Exception("Lists don't match!");
        }
        Long sequentialTimeAvg = sequentialTimeSum/20;
        Long parallelTimeAvg = parallelTimeSum/20;

        System.out.println(sequentialTime);
        System.out.println(parallelTime);

        System.out.println("Avg time for sequential sorting is " + sequentialTimeAvg + " milliseconds of " + listSize + " el list." );
        System.out.println("Avg time for parallel sorting is " +  parallelTimeAvg + " milliseconds of " + listSize + " el list.");

    }
}