package ACP4;

import java.util.concurrent.Semaphore;

public class JSONStreamProtector {
    private Semaphore semaphore = new Semaphore(1, true);

    public JSONStreamProtector() {
    }

    public void protectJSONStream(JSONStream aJsonStream) {
        aJsonStream.setProtector(this);
    }

    protected void claim() throws InterruptedException {
        this.semaphore.acquire(1);
    }

    protected void free() {
        this.semaphore.release(1);
    }
}
