package sorts.oddEven;

/**
 * Created by Стас on 7/2/2017.
 */
public class Barrier {

    private int size;
    private int initiated = 0;

    public Barrier(int s) {
        if (s >= 1) {
            size = s;
        } else {
            size = 1;
        }
    }

    public synchronized void meet()  {
        initiated++;
        if (initiated == size) {
            initiated = 0;
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
