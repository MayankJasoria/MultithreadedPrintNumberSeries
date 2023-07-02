public class PrintNumberSeries {
    private final int n;
    private int count;
    private final Semaphore zeroSemaphore;
    private final Semaphore oddSemaphore;
    private final Semaphore evenSemaphore;

    public PrintNumberSeries(int n) {
        this.n = n;
        this.count = 1;
        zeroSemaphore = new Semaphore(1);
        oddSemaphore = new Semaphore(1);
        evenSemaphore = new Semaphore(0);
    }

    public void printZero() {
        while (count < n) {
            try {
                zeroSemaphore.acquire();
            } catch (InterruptedException e) {
                // make thread attempt to acquire semaphore again
                continue;
            }
            System.out.print(0);
            oddSemaphore.release();
            evenSemaphore.release();
        }
        // ensure termination
        oddSemaphore.release();
        oddSemaphore.release();
        evenSemaphore.release();
        evenSemaphore.release();
    }

    public void printOdd() {
        while (count < n) {
            try {
                oddSemaphore.acquire();
            } catch (InterruptedException e) {
                // attempt operation again
                continue;
            }
            try {
                oddSemaphore.acquire();
            } catch (InterruptedException e) {
                // release one acquired semaphore and try again
                oddSemaphore.release();
                continue;
            }
            System.out.print(count++);
            zeroSemaphore.release();
        }
        zeroSemaphore.release();
        evenSemaphore.release();
    }

    public void printEven() {
        while (count < n) {
            try {
                evenSemaphore.acquire();
            } catch (InterruptedException e) {
                // attempt whole operation again
                continue;
            }
            try {
                evenSemaphore.acquire();
            } catch (InterruptedException e) {
                // release the one acquired semaphore and attempt again
                evenSemaphore.release();
                continue;
            }
            System.out.print(count++);
            zeroSemaphore.release();
        }
        zeroSemaphore.release();
        oddSemaphore.release();
    }
}