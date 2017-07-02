import sorts.BucketSort;
import sorts.bitonicSort.BitonicSort;
import sorts.mergeSort.MergeSort;
import sorts.oddEven.Barrier;
import sorts.oddEven.EvenOddThread;
import sorts.quickSort.Quicksort;
import sorts.shellSort.ShellSort;
import utils.Constants;
import utils.FileUtil;
import utils.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
        //testMergeSort(1);
        //testEvenOddSort(16);
        //testShellSort();
        //testBitonicSort(16);
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

    private static void testBitonicSort(int threadsNumber){
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        long startTime = Log.d("Start BitonicSort with numberThreads=" + threadsNumber);
        BitonicSort.sort(data,threadsNumber);
        Log.d("Finish BitonicSort sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }

    private static void testEvenOddSort(int threadsNumber) {
        int[] list = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        Barrier barrier = new Barrier((list.length + 1) / 2);
        Barrier finish = new Barrier((list.length + 1) / 2 + 1);
        long startTime = Log.d("Start EvenOdd");
        ExecutorService executor = Executors.newFixedThreadPool(threadsNumber);
        for (int i = 0; i < (list.length + 1) / 2; i++) {
           executor.execute(new EvenOddThread(list,i*2,barrier,finish)); //.start();
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        Log.d("Finish EvenOddSort sort",startTime);
        finish.meet();
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,list);
    }

    private static void testSimpleSort() {
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        long startTime = Log.d("Start simple sort");
        Arrays.sort(data);
        Log.d("Finish simple sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }


    private static void testShellSort() {
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        long startTime = Log.d("Start shell sort");
        new ShellSort().sort(data);
        Log.d("Finish shell sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }





}
