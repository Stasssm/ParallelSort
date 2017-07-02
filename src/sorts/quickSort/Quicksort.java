package sorts.quickSort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * Convenience methods, in-place partition implementation
 */
public class Quicksort {

    /**
     * Convenience method.  Invokes a ForkJoinQuickSortTask in the provided pool, blocking until done.
     * @param pool executes sorting tasks
     * @param a array to sort
     * @return sorted array
     */
    public static int[] forkJoinQuicksort(ForkJoinPool pool, int[] a) {
        pool.invoke(new ForkJoinQuicksortTask(a));
        return a;
    }

    /**
     * Example invocation:
     * @param a array to partition
     * @param left lower bound for partition
     * @param right upper bound for partition (inclusive)
     * @return pivot index - assert(a[i] < a[j]) for all i where {left <= i <= pivot}
     * and all j where {j > pivot}
     * @see ForkJoinQuicksortTask
     */
    public static int partition(int[] a, int left, int right) {
        // chose middle value of range for our pivot
        int pivotValue = a[middleIndex(left, right)];

        --left;
        ++right;

        while (true) {
            do
                ++left;
            while (a[left] < pivotValue);

            do
                --right;
            while (a[right] > pivotValue);

            if (left < right) {
                int tmp = a[left];
                a[left] = a[right];
                a[right] = tmp;
            } else {
                return right;
            }
        }
    }

    // calculates middle index without integer overflow
    private static int middleIndex(int left, int right) {
        return left + (right - left) / 2;
    }
}