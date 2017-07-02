package sorts.bitonicSort;

import sorts.mergeSort.TopDownMergeSortTask;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Стас on 7/2/2017.
 */
public class BitonicSort {

    public static void sort(int[] a,int poolSize) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(poolSize);
        forkJoinPool.invoke(new BitonicSorterTask(a,0, a.length, true));
    }


}
