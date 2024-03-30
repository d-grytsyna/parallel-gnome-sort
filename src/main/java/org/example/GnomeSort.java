package org.example;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
public class GnomeSort {
    public static void sequentialGnomeSort(List<Integer> list, int startIndex, int endIndex){
        if(list.size()<=1) return;
        try {
            int index = startIndex;
            while (index<endIndex){
                if(index==startIndex){
                    index++;
                }
                if(list.get(index)>=list.get(index-1)){
                    index++;
                }else{
                        Integer temp;
                        temp = list.get(index);
                        list.set(index, list.get(index-1));
                        list.set(index-1, temp);
                        index--;}
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static List<Integer> parallelGnomeSort(List<Integer> list2, int minElement,
                                                  int maxElement, int threadNum, int depth){
        ForkJoinPool pool = new ForkJoinPool(threadNum);
        ParallelSortTask sortTask = new ParallelSortTask(list2, minElement, maxElement+1, depth);
        return pool.invoke(sortTask);

    }

}
