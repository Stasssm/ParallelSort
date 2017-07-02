import sorts.BucketSort;
import sorts.mergeSort.MergeSort;
import sorts.quickSort.Quicksort;
import utils.Constants;
import utils.FileUtil;
import utils.Log;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by Стас on 7/1/2017.
 */
public class Core {


    public static void main(String[] args) {
        FileUtil.generateRandomData(Constants.TEST_FILE_INPUT);
        //testBucketSort(true,100);
        //testSimpleSort();
        //testQuickSort(40);
        testMergeSort(1);
       // testQuickSort(1);
    }

    private static void testBucketSort(boolean isParallel, int bucketSize) {
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        BucketSort bucketSort = new BucketSort();
        long startTime = Log.d("Start parallel bucket sort");
        bucketSort.sort(data,bucketSize,isParallel);
        Log.d("Finish parallel bucket sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }


    private static void testQuickSort(int threadsNumber){
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadsNumber);
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        long startTime = Log.d("Start QuickSort with numberThreads=" + threadsNumber);
        Quicksort.forkJoinQuicksort(forkJoinPool,data);
        Log.d("Finish QuickSort sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }

    private static void testMergeSort(int threadsNumber){
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        long startTime = Log.d("Start MergeSort with numberThreads=" + threadsNumber);
        MergeSort.sort(data,threadsNumber);
        Log.d("Finish MergeSort sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }




    private static void testSimpleSort() {
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        long startTime = Log.d("Start simple sort");
        Arrays.sort(data);
        Log.d("Finish simple sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }








}
