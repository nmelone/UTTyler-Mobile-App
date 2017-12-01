import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParsing {
    public static ArrayList StudentParse(String inputString){
        ArrayList<String> matches = new ArrayList();
        ArrayList<String> OMatches = new ArrayList();
        Pattern pattern = Pattern.compile("(([\\w\\d\\s&@.:\\/-]+)(,){0,1}([\\w\\d\\s&@.]+))");
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
        Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        for(String thisString : OMatches) {
            matcher = pattern.matcher(thisString);
            int i = 0;
            while (matcher.find()) {
                if(i == 1 || i == 3 || (i > 4 && i%2 == 1))
                    matches.add(matcher.group());
                i++;
            }
        }
        return matches;
    }
    
    public static ArrayList ClassParse(String inputString){
        ArrayList<String> matches = new ArrayList();
        ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
        Pattern pattern3 = Pattern.compile("(([\\w\\d\\s&@.:\\/-]+)(,){0,1}([\\w\\d\\s&@.\\/-]+))");
        Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        for(String thisString : OMatches) {
            //matcher = pattern.matcher(thisString);
        	matcher = pattern3.matcher(thisString);
            int i = 0;
            int x = 9;
            while (matcher.find()) {
                if(i%2!=0) {
                	if(i == x){
                		x+=12;
                		if(matcher.group().charAt(1)=='W')
                			matches.add("M\\"+matcher.group());
                		else if(matcher.group().charAt(1)=='T')
                			matches.add("T\\"+matcher.group());
                		else
                			matches.add(matcher.group());
                	}
                	else
                		matches.add(matcher.group());
                }
                i++;
            }
        }
        return matches;
    }
    
    public static ArrayList CourseParse(String inputString){
    	ArrayList<String> matches = new ArrayList();
        ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
        Pattern pattern3 = Pattern.compile("[\\w\\s\\d.]+");
        Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        for(String thisString : OMatches) {
        	matcher = pattern3.matcher(thisString);
        	int i = 0;
        	while (matcher.find()) {
        		if(i%2!=0) {
        			matches.add(matcher.group());
        		}
        		i++;
        	}
        }
        return matches;
    }
    
    public static ArrayList TablesParse(String inputString){
    	ArrayList<String> matches = new ArrayList();
        ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
        Pattern pattern3 = Pattern.compile("[\\w\\s\\d.]+");
        Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        /*for(String thisString : OMatches) {
        	matcher = pattern3.matcher(thisString);
        	int i = 0;
        	while (matcher.find()) {
        		if(i%2!=0) {
        			matches.add(matcher.group());
        		}
        		i++;
        	}
        }*/
        matches = OMatches;
        return matches;
    }
    
    public static ArrayList EmployeesParse(String inputString){
    	ArrayList<String> OMatches = new ArrayList();
    	Pattern pattern2 = Pattern.compile("([\\w\\s\\d]+)|\"\"");
    	Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        return OMatches;
    }
    
    public static ArrayList RequirementParse(String inputString){
    	ArrayList<String> OMatches = new ArrayList();
        //Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
    	Pattern pattern2 = Pattern.compile("\"[\\w\\s\\d\\(\\)\\:&.]+\"");
    	Matcher matcher = pattern2.matcher(inputString);
    	int i = 1;
        while(matcher.find()) {
        	if(i==2)
        		OMatches.add("\"ALL COURSES (CORE\\/MAJOR\\/MINOR\\/GENERAL ELECTIVES)\"");
            OMatches.add(matcher.group());
            i++;
        }
        return OMatches;
    }
    
    public static ArrayList DepartmentsParse(String inputString){
    	ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("([\\w\\s\\d]+,[\\w\\s\\d&]+)|[\\w\\s\\d&]+");
    	Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        return OMatches;
    }
    
    public static ArrayList RqlncParse(String inputString){
    	ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("[\\w\\s\\d]+");
    	Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        return OMatches;
    }
    
    public static ArrayList SpecialParse(String inputString){
    	ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("[{\"][\\ -z]+[\"}]");
    	Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        return OMatches;
    }
    
    public static ArrayList RqlndParse(String inputString){
    	ArrayList<String> OMatches = new ArrayList();
        Pattern pattern2 = Pattern.compile("(([\\w\\s\\d&\\/\\(\\). \\\\ ]+,)+([\\w\\s\\d\\&\\/\\(\\). \\\\ ]+))|[\\w\\s\\d\\&\\/\\(\\). \\\\ ]+");
    	Matcher matcher = pattern2.matcher(inputString);
        while(matcher.find()) {
            OMatches.add(matcher.group());
        }
        return OMatches;
    }
    
}
