public abstract class SortList {

    public Entry head;
    public  int listLengthAfterAdds=0;
    public  int listLengthAfterRemove=0;
    public  int containSuccesses=0;
    public  int containFailures=0;
    public  int removeSuccesses=0;
    public  int removeFailures=0;
    public SortList() {
        this.head = new Entry(Integer.MIN_VALUE);
       this.head.next =new Entry(Integer.MAX_VALUE);
    }

    public  abstract  boolean add(Integer obj);
    public  abstract  boolean remove(Integer obj);
    public  abstract  boolean contain(Integer obj);

    public void printList(){
        Entry curr = this.head;
        while (curr != null){
            System.out.println(curr.object);
            curr = curr.next;

        }
    }
    public boolean checkSort(){

        Entry prev = this.head;
        Entry curr = prev.next;
        if (curr.object.compareTo(prev.object) > 0) {
            prev = curr;
            curr = prev.next;
        }
       else {
            return false;
        }
       return true;
    };
}
