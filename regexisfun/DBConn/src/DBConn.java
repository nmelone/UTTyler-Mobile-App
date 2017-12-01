import java.util.Scanner;
import java.util.ArrayList;

public class DBConn{
	static String[] theArgs = new String[3];
	public static void main(String[] args) {
/*		Scanner cin = new Scanner(System.in);
		System.out.println("Please state your purpose (login,query");
		theArgs[0] = cin.nextLine();
		System.out.println("Please enter a query statement:");
		theArgs[1] = cin.nextLine();*/
		StudentBiz();
		SpaceBetween();
		ClassBiz();
		SpaceBetween();
		CourseBiz();
		SpaceBetween();
		ListTables();
	}
	
	public static void SpaceBetween(){
		for(int x = 0; x < 20; x++)
			System.out.print("-");
		System.out.println("");
	}
	
	private static void StudentBiz(){
		theArgs[0] = "query";
		theArgs[1] = "select * from student";
		theArgs[2] = null;
		ActConn stuConn = new ActConn(theArgs);
		ArrayList<String> students = StringParsing.StudentParse(stuConn.makeTheConnection());
		int q = 1;
		for(String i : students){
			System.out.print(i);
			System.out.print(" , ");
			if(q%7==0)
				System.out.println(" ");
			q++;
		}
	}
	
	private static void ClassBiz(){
		theArgs[0] = "query";
		theArgs[1] = "select * from class";
		ActConn claConn = new ActConn(theArgs);
		ArrayList<String> classes = StringParsing.ClassParse(claConn.makeTheConnection());
		int q = 1;
		for(String i : classes){
			if(i.equals("001")==false){
				System.out.print(i);
				System.out.print(" , ");
				if(q%5==0){
					System.out.println("");
					q=0;
				}
				q++;
			}
		}
	}
	
	private static void CourseBiz(){
		theArgs[0] = "query";
		theArgs[1] = "select * course";
		ActConn couConn = new ActConn(theArgs);
		ArrayList<String> courses = StringParsing.CourseParse(couConn.makeTheConnection());
		int q = 1;
		for(String i : courses){
			System.out.println(i);
		}
	}
	
	private static void ListTables(){
		theArgs[0] = "query";
		theArgs[1] = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE'";
		ActConn couConn = new ActConn(theArgs);
		ArrayList<String> courses = StringParsing.TablesParse(couConn.makeTheConnection());
		int q = 1;
		for(String i : courses){
			System.out.println(i);
		}
	}
}
