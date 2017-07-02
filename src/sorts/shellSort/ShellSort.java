package sorts.shellSort;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Стас on 7/2/2017.
 */
public class ShellSort {

    public void sort(int[] a) {
        int i,j,k;
        int N = a.length;

        for(k = N/2; k > 0; k /=2) {
            ExecutorService executor = Executors.newFixedThreadPool(k);
            for (i = k; i < N; i++) {
                executor.execute(new ParallelShell(i,a,k));
            }
            executor.shutdown();
            while (!executor.isTerminated()) {

            }
//            for(i = k; i < N; i++) {
//                int t = a[i];
//                for (j = i; j >= k; j -= k) {
//                    if (t < a[j - k])
//                        a[j] = a[j - k];
//                    else
//                        break;
//                }
//                a[j] = t;
//            }
        }
    }

    private class ParallelShell implements Runnable {

        int i;
        int[] a;
        int k;

        public ParallelShell(int i, int[] a, int k) {
            this.i = i;
            this.a = a;
            this.k = k;
        }

        @Override
        public void run() {
            int t = a[i];
            int j = 0;
            for (j = i; j >= k; j -= k) {
                if (t < a[j - k])
                    a[j] = a[j - k];
                else
                    break;
            }
            a[j] = t;
        }


    }


}
