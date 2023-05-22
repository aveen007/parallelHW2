// this class is one that I made to help me get the numbers out of the output so that I can add them to my excel
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class outputExtracter {
    public static void main(String[] args) {
        String fileName = "D:\\output.txt";
        String numbers = extractNumbers(fileName);
        System.out.println(numbers);
    }

    public static String extractNumbers(String fileName) {
        String res="";
        List<Integer> numbers = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    int number = Integer.parseInt(matcher.group());
                   res+=number+" ";
                }
               // res+=;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
}

