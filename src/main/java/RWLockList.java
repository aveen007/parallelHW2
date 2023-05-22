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
            if (curr != null && curr.object.equals(obj) || prev.object.equals(obj)) {

                return false;
            } else {
                Entry newEntry = new Entry(obj);
                newEntry.next = curr;
                prev.next = newEntry;
                this.listLengthAfterAdds += 1;
                this.listLengthAfterRemove += 1;

                return true;
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public boolean remove(Integer obj) {

            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr!=null&&curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if ( curr!=null&&curr.object.equals(obj)) {
                curr.lock.writeLock().lock();
                try {
                    if (prev == null) {
                        head = curr.next;
                    } else {
                        prev.next = curr.next;
                    }

                    listLengthAfterRemove--;
                    removeSuccesses++;
                return true;
                }
                finally {

                         curr.lock.writeLock().unlock();

                }

            } else {
                removeFailures++;
                return false;
            }


    }

    @Override
    public boolean contain(Integer obj) {
        try {
            lock.writeLock().lock();
            lock.readLock().lock();
            if (this.head.next == null) {
                // if the List is empty
                containFailures++;
                return false;
            }
            Entry prev = this.head;
            Entry curr = prev.next;
            while (curr != null && curr.object.compareTo(obj) < 0) {
                prev = curr;
                curr = prev.next;
            }
            if (curr != null && curr.object.equals(obj) || prev.object.equals(obj)) {
                containSuccesses++;
                return true;
            } else {
                containFailures++;
                return false;
            }
        } finally {
            lock.writeLock().unlock();
            lock.readLock().unlock();
        }
    }

}
