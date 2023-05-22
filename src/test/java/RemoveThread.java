public class RemoveThread extends TestThread implements Runnable{
    public RemoveThread(RandomSequenceGenerator randomSequenceGenerator, int seqPart, SortList list) {
        super(randomSequenceGenerator, seqPart, list);
    }

    @Override
    public void run() {
        for (int i=0; i<nums.length;i++){
            list.remove(nums[i]);
        }
    }
}
