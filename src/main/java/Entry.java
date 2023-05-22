import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Entry {

    public Integer object;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Entry next;

    public Entry(Integer object) {
        this.object = object;
    }
}
