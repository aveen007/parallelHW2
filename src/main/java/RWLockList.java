import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockList extends SortList {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public RWLockList() {
        super();
    }

    @Override
    public boolean add(Integer obj) {
        try {
            lock.writeLock().lock();

            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                removeFailures++;
                return false;
            } else {
                Entry newEntry = new Entry(obj);
                newEntry.next = curr;
                prev.next = newEntry;
                this.listLengthAfterAdds += 1;
                this.listLengthAfterRemove += 1;
                removeSuccesses++;
                return true;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean remove(Integer obj) {
       // try {
           //   lock.writeLock().lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj)) {
                lock.writeLock().lock();
                try {
                    if (prev == null) {
                        head = curr.next;
                    } else {
                        prev.next = curr.next;
                    }

                    listLengthAfterRemove--;
                return true;
                }
                finally {
                         lock.writeLock().unlock();

                }

            } else {
                return false;
            }
       /* } finally {
                 lock.writeLock().unlock();

        }*/

    }

    @Override
    public boolean contain(Integer obj) {
        try {
            lock.readLock().lock();
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr.object.equals(obj) || prev.object.equals(obj)) {
                containSuccesses++;
                return true;
            } else {
                containFailures++;
                return false;
            }
        } finally {
            lock.readLock().unlock();
        }
    }

}
