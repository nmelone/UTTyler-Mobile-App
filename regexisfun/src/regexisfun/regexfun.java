package regexisfun;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regexfun {

    public static void main(String[] args) {
//      System.out.println("Starting program");
        String inputString = "[{\"sti_id\":\"5003654321\",\"first_name\":\"Ben\",\"minit\":\"\",\"last_name\":\"Affleck\",\"user_id\":\"baffleck\",\"password\":\"abc123\",\"email\":\"baffleck@patriots.uttyler.edu\",\"major\":\"Computer Science\"},[{\"sti_id\":\"5003214654\",\"first_name\":\"Bruce\",\"minit\":\"\",\"last_name\":\"Willis\",\"user_id\":\"bwillis\",\"password\":\"abc123\",\"email\":\"bwillis@patriots.uttyler.edu\",\"major\":\"Computer Systems\"}]";
        ArrayList<String> matches = new ArrayList();
        ArrayList<String> OMatches = new ArrayList();
        Pattern pattern = Pattern.compile("([a-zA-Z0-9.@_]+\\s*)+[^\\n^\"]");
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
        Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
//          System.out.println(matcher.group());
        }
//      System.out.println("initial regex complete, starting secondary");   
        for(String thisString : OMatches) {
            matcher = pattern.matcher(thisString);
            int i = 0;
            while (matcher.find()) {
                if(i == 1||i==3||(i>5&&i%2==0)) {
//                  System.out.println(matcher.group());
                    matches.add(matcher.group());
                }
                i++;
            }
        }
//      System.out.println("Program ended");
    }
}