import java.util.Random;

public class RandomSequenceGenerator {


    int seed;

    Random random ;


    int maxNum;

    public RandomSequenceGenerator(int seed, int maxNum) {
        this.seed = seed;
        this.random = new Random(seed);
        this.maxNum = maxNum;
    }

    public  int next(){
        return  random.nextInt(maxNum);
    }
}
