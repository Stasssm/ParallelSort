package sorts;

import utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Стас on 7/1/2017.
 */
public class BucketSort {

    private static final int DEFAULT_BUCKET_SIZE = 5;
    private static final int NUMBER_THREADS = 10;

    public void sortParallel(int[] array) {
        sort(array, DEFAULT_BUCKET_SIZE,true);
    }

    public void sort(int[] array) {
        sort(array, DEFAULT_BUCKET_SIZE,false);
    }


    public void sort(int[] array, int bucketSize , boolean parallel) {
        if (array.length == 0) {
            return;
        }

        // Determine minimum and maximum values
        long startTime = Log.d("Start Determine minimum and maximum values");
        Integer minValue = array[0];
        Integer maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        Log.d(" Finish Determine minimum and maximum values",startTime);

        long initTime = Log.d("Start Initialise buckets");
        // Initialise buckets
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        Log.d("Finish Initialise buckets",initTime);

        long distTime = Log.d("Start distribute buckets");
        // Distribute input array values into buckets
        for (int i = 0; i < array.length; i++) {
            buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
        }
        Log.d("Finish distribute buckets",distTime);

        if (parallel) {
            parallelBucketSort(array, buckets);
        } else {
            simpleBucketSort(array,buckets);
        }
    }

    private void parallelBucketSort(int[] finalArray, List<List<Integer>> buckets) {
        long distTime = Log.d("Start sort");
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_THREADS);
        for (List<Integer> bucket : buckets) {
            Runnable worker = () -> {
                Collections.sort(bucket);
                //Log.d(Thread.currentThread().getName() + " sorted");
            };
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        Log.d("Finish sort", distTime );
        int currentIndex = 0;
        for (List<Integer> elementList : buckets) {
            for (Integer element : elementList) {
                finalArray[currentIndex++] = element;
            }
        }
        System.out.println("_______________________");
    }

    private void simpleBucketSort(int[] finalArray, List<List<Integer>> buckets) {
        long distTime = Log.d("Start sort");
        int currentIndex = 0;
        for (int i = 0; i < buckets.size(); i++) {
            Integer[] bucketArray = new Integer[buckets.get(i).size()];
            bucketArray = buckets.get(i).toArray(bucketArray);
            Arrays.sort(bucketArray);
            for (int j = 0; j < bucketArray.length; j++) {
                finalArray[currentIndex++] = bucketArray[j];
            }
        }
        Log.d("Finish sort", distTime );
        System.out.println("_______________________");
    }







}
