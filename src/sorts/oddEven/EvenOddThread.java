package sorts.oddEven;

/**
 * Created by Стас on 7/2/2017.
 */
public class EvenOddThread extends Thread {

    private int [] prob;
    private int id;
    private Barrier barrier, done;

    public EvenOddThread (int [] unsorted, int i, Barrier b, Barrier d) {
        prob = unsorted;
        id = i;
        barrier = b;
        done = d;
    }

    public void run () {
        for (int i = 0; i < prob.length / 2; i++) {
            if ((id > 0) && (prob[id - 1] > prob[id])) swap(prob,id - 1, id);
          //  barrier.meet();
            if ((id < prob.length - 1) && (prob[id] > prob[id + 1])) swap(prob, id, id + 1);
         //   barrier.meet();
        }
      //  done.meet();
    }

    private void swap(int[] prob, int id1, int id2) {
        int T = prob[id1];
        prob[id1] = prob[id2];
        prob[id2] = T;
    }
}



