public class ContainThread extends TestThread implements Runnable{

    public ContainThread(RandomSequenceGenerator randomSequenceGenerator, int seqPart, SortList list) {
        super(randomSequenceGenerator, seqPart, list);
    }

    @Override
    public void run() {

        for (int i=0; i<nums.length;i++){
            list.contain(nums[i]);

        }

    }
}
