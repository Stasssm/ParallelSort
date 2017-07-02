import sorts.BucketSort;
import utils.Constants;
import utils.FileUtil;
import utils.Log;

import java.util.Arrays;

/**
 * Created by Стас on 7/1/2017.
 */
public class Core {


    public static void main(String[] args) {
        FileUtil.generateRandomData(Constants.TEST_FILE_INPUT);
        testBucketSortParallel();
        testBucketSort();
      //  testSimpleSort();
    }

    private static void testBucketSortParallel() {
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        BucketSort bucketSort = new BucketSort();
        long startTime = Log.d("Start parallel bucket sort");
        bucketSort.sort(data,100,true);
        Log.d("Finish parallel bucket sort",startTime);
        FileUtil.toFile(Constants.TEST_FILE_OUTPUT,data);
    }

    private static void testBucketSort() {
        int[] data = FileUtil.fromFile(Constants.TEST_FILE_INPUT);
        BucketSort bucketSort = new BucketSort();
        long startTime = Log.d("Start bucket sort");
        bucketSort.sort(data,100,false);
        Log.d("Finish bucket sort",startTime);
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
