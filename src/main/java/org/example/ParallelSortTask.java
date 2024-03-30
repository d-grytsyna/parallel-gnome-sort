package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParallelSortTask extends RecursiveTask<List<Integer>> {
    private final List<Integer> list;
    private final int min;
    private final int max;
    private final int depth;

    public ParallelSortTask(List<Integer> list, int min, int max, int depth) {
        this.list = list;
        this.min = min;
        this.max = max;
        this.depth = depth;
    }

    @Override
    protected List<Integer> compute() {
        if (list.size()<=50 || depth == 0) {
             GnomeSort.sequentialGnomeSort(list, 0, list.size());
             return list;
        }
        int mid = min + (max - min) / 2;
        ParallelSortTask leftTask = new ParallelSortTask(getSublist(list, min, mid), min, mid, depth - 1);
        ParallelSortTask rightTask = new ParallelSortTask(getSublist(list, mid, max), mid, max, depth - 1);
        leftTask.fork();
        rightTask.fork();
        List<Integer> newList = new ArrayList<>();
        newList.addAll(leftTask.join());
        newList.addAll(rightTask.join());
        return newList;
    }
    private List<Integer> getSublist(List<Integer> currentList, int min, int max) {
        List<Integer> sublist = new ArrayList<>();
        for (Integer el : currentList) {
            if (el >= min && el < max) {
                sublist.add(el);
            }
        }
        return sublist;
    }

}
