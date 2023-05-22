public class AddThread extends TestThread implements Runnable {
    public AddThread(RandomSequenceGenerator randomSequenceGenerator, int seqPart, SortList list) {
        super(randomSequenceGenerator, seqPart, list);
    }

    @Override
    public void run() {

        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
    }
}
