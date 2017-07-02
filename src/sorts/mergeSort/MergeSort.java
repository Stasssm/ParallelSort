package sorts.mergeSort;

import java.util.concurrent.ForkJoinPool;

/**
 * Created by Стас on 7/2/2017.
 */
public class MergeSort {

    public static void sort(int[] a,int poolSize) {
        int[] helper = new int[a.length];
        ForkJoinPool forkJoinPool = new ForkJoinPool(poolSize);
        forkJoinPool.invoke(new TopDownMergeSortTask(a, helper, 0, a.length-1));
    }


}
