package sorts.bitonicSort;

import java.util.concurrent.RecursiveAction;

/**
 * Created by Стас on 7/2/2017.
 */
public class BitonicSorterTask extends RecursiveAction {

    private int[] sortedArray;
    int lo;
    int n;
    boolean isAscending;


    public BitonicSorterTask(int[] sortedArray, int lo, int n, boolean isAscending) {
        this.sortedArray = sortedArray;
        this.lo = lo;
        this.n = n;
        this.isAscending = isAscending;
    }

    @Override
    protected void compute() {
        if (n > 1) {
            int m = n / 2;
            BitonicSorterTask bitonicSorter = new BitonicSorterTask(sortedArray,lo, m, !isAscending);
            BitonicSorterTask bitonicSorter1 = new BitonicSorterTask(sortedArray,lo + m, n - m, isAscending);
            invokeAll(bitonicSorter,bitonicSorter1);
            bitonicMerge(lo, n, isAscending);
        }
    }



    private void bitonicMerge(int lo, int n, boolean dir) {
        if (n > 1) {
            int m = greatestPowerOfTwoLessThan(n);
            for (int i = lo; i < lo + n - m; i++) {
                compare(i, i + m, dir);
            }
            bitonicMerge(lo, m, dir);
            bitonicMerge(lo + m, n - m, dir);
        }
    }

    private void compare(int i, int j, boolean isSortingDirection) {
        if (isSortingDirection == (sortedArray[i] > sortedArray[j]))
            exchange(i, j);
    }

    private void exchange(int i, int j) {
        final int t = sortedArray[i];
        sortedArray[i] = sortedArray[j];
        sortedArray[j] = t;
    }

    private int greatestPowerOfTwoLessThan(int n) {
        int k = 1;
        while (k < n)
            k = k << 1;
        return k >> 1;
    }

}
