package sorts.mergeSort;

import java.util.concurrent.RecursiveAction;

/**
 * Created by Стас on 7/2/2017.
 */
public class TopDownMergeSortTask extends RecursiveAction {
    private static final long serialVersionUID = -749935388568367268L;
    private final int[] a;
    private final int[] helper;
    private final int lo;
    private final int hi;

    public TopDownMergeSortTask(int[] a, int[] helper, int lo, int hi){
        this.a = a;
        this.helper = helper;
        this.lo = lo;
        this.hi = hi;
    }
    @Override
    protected void compute() {
        if (lo>=hi) return;
        int mid = lo + (hi-lo)/2;
        TopDownMergeSortTask left = new TopDownMergeSortTask(a, helper, lo, mid);
        TopDownMergeSortTask right = new TopDownMergeSortTask(a, helper, mid+1, hi);
        invokeAll(left, right);
        merge(this.a, this.helper, this.lo, mid, this.hi);


    }
    private void merge(int[] a, int[] helper, int lo, int mid, int hi){
        for (int i=lo;i<=hi;i++){
            helper[i]=a[i];
        }
        int i=lo,j=mid+1;
        for(int k=lo;k<=hi;k++){
            if (i>mid){
                a[k]=helper[j++];
            }else if (j>hi){
                a[k]=helper[i++];
            }else if(isLess(helper[i], helper[j])){
                a[k]=helper[i++];
            }else{
                a[k]=helper[j++];
            }
        }
    }
    private boolean isLess(int a, int b) {
        return a < b;
    }
}

