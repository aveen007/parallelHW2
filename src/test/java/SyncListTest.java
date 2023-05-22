import junit.framework.TestCase;

import java.util.ArrayList;

public class SyncListTest extends TestCase {

    public void testAddList() {

        SyncList syncList = new SyncList();

        syncList.add(1);
        syncList.add(2);
        syncList.add(3);
        syncList.add(Integer.MIN_VALUE);
        syncList.add(3);
        System.out.println(syncList.contain(5));
        System.out.println(syncList.contain(2));
        syncList.remove(3);

        syncList.printList();


    }

    public void testRandom() {
        RandomSequenceGenerator randomSequenceGenerator = new RandomSequenceGenerator(0, 80_000);
        for (int i = 0; i < 10; i++) {
            System.out.print(randomSequenceGenerator.next() + " ");
        }
    }

    int randLen = 50_000;

    public void testHelp(SortList list, String label) {

        RandomSequenceGenerator seq = new RandomSequenceGenerator(0, 80000);

        ArrayList<Thread> addThreads = new ArrayList<>();
        ArrayList<Thread> removeThreads = new ArrayList<>();
        ArrayList<Thread> containThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            AddThread addThread = new AddThread(seq, randLen / 3, list);
            Thread thread = new Thread(addThread);


//////////////
            RemoveThread removeThread = new RemoveThread(seq, randLen / 3, list);
            Thread thread2 = new Thread(removeThread);

            ////////////
            ContainThread containThread = new ContainThread(seq, randLen / 3, list);
            Thread thread3 = new Thread(containThread);


            addThreads.add(thread);
            removeThreads.add(thread2);
            containThreads.add(thread3);

        }


        long start = System.currentTimeMillis();


        addThreads.stream().forEach(e -> e.start());
        addThreads.stream().forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        long end = System.currentTimeMillis() - start;

        System.out.println("Add " + label + " execution task: " + end + " (ms)");
        System.out.println(" List length after add  " + list.listLengthAfterAdds);

        //////////
        long start2 = System.currentTimeMillis();


        containThreads.stream().forEach(e -> e.start());
        containThreads.stream().forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        long end2 = System.currentTimeMillis() - start2;
        System.out.println("Contain " + label + " execution task: " + end2 + " (ms)");
        System.out.println("Total number of successes found: "+list.containSuccesses+", failures found: "+list.containFailures);


        ///////////
        long start1 = System.currentTimeMillis();


        removeThreads.stream().forEach(e -> e.start());
        removeThreads.stream().forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        long end1 = System.currentTimeMillis() - start1;
        System.out.println("Remove " + label + " execution task: " + end1 + " (ms)");
        System.out.println(" List length after remove  " + list.listLengthAfterRemove);
        System.out.println("Total number of successes removed: "+list.removeSuccesses+", failures removed: "+list.removeFailures);
        System.out.println("============");


    }

    public void testRun() {

        SyncList syncList = new SyncList();
        LockList lockList = new LockList();
        RWLockList rwLockList = new RWLockList();

        testHelp(syncList, "Synchronization");
        assertEquals(syncList.checkSort(), true);
        System.out.println(syncList.checkSort());

        testHelp(lockList, "Lock");
        assertEquals(lockList.checkSort(), true);
        System.out.println();

        testHelp(rwLockList, "RW Lock");
        assertEquals(rwLockList.checkSort(), true);

        System.out.println();


    }

    public void testSort() {

    }
}
