public abstract  class TestThread {

    protected SortList list;
    Integer [] nums;

    public TestThread(RandomSequenceGenerator randomSequenceGenerator, int seqPart, SortList list) {
        this.list = list;
        this.nums = new Integer[seqPart];

        for(int i=0; i<seqPart;i++){
            nums[i]=randomSequenceGenerator.next();
        }
    }
}
