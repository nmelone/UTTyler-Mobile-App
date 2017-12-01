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
		DegreeBiz();
		SpaceBetween();
		DepartmentBiz();
		SpaceBetween();
		EmployeeBiz();
		SpaceBetween();
		LogFileBiz();
		SpaceBetween();
		RequirementBiz();
		SpaceBetween();
		Rq_ln_courseBiz();
		SpaceBetween();
		Rq_ln_transcBiz();
		SpaceBetween();
		Stu_enrollBiz();
		SpaceBetween();
		TransCourseBiz();
		SpaceBetween();
		TransStudentBiz();
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
		System.out.println("");
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
		System.out.println("");
	}
	
	private static void CourseBiz(){
		theArgs[0] = "query";
		theArgs[1] = "select * from course";
		ActConn couConn = new ActConn(theArgs);
		ArrayList<String> courses = StringParsing.CourseParse(couConn.makeTheConnection());
		int q = 1;
		for(String i : courses){
			System.out.println(i);
		}
		System.out.println("");
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
		System.out.println("");
	}
	
	private static void DegreeBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from degree";
		ActConn degConn = new ActConn(theArgs);
		ArrayList<String> degrees = StringParsing.CourseParse(degConn.makeTheConnection());
		int q = 1;
		for(String i : degrees) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void DepartmentBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from department";
		ActConn depConn = new ActConn(theArgs);
		ArrayList<String> departments = StringParsing.CourseParse(depConn.makeTheConnection());
		int q = 1;
		for(String i : departments) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void EmployeeBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from employee";
		ActConn empConn = new ActConn(theArgs);
		ArrayList<String> employees = StringParsing.CourseParse(empConn.makeTheConnection());
		int q = 1;
		for(String i : employees) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void LogFileBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from logfile";
		ActConn logConn = new ActConn(theArgs);
		ArrayList<String> logFiles = StringParsing.CourseParse(logConn.makeTheConnection());
		int q = 1;
		for(String i : logFiles) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void RequirementBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from requirement";
		ActConn reqConn = new ActConn(theArgs);
		ArrayList<String> requirements = StringParsing.CourseParse(reqConn.makeTheConnection());
		int q = 1;
		for(String i : requirements) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void Rq_ln_courseBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from rq_ln_course";
		ActConn rqlnConn = new ActConn(theArgs);
		ArrayList<String> rqlns = StringParsing.CourseParse(rqlnConn.makeTheConnection());
		int q = 1;
		for(String i : rqlns) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void Rq_ln_transcBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from rq_ln_transc";
		ActConn rqlnConn = new ActConn(theArgs);
		ArrayList<String> rqlns = StringParsing.CourseParse(rqlnConn.makeTheConnection());
		int q = 1;
		for(String i : rqlns) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void Stu_enrollBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from stu_enroll";
		ActConn enrConn = new ActConn(theArgs);
		ArrayList<String> enrollments = StringParsing.CourseParse(enrConn.makeTheConnection());
		int q = 1;
		for(String i : enrollments) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void TransCourseBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from trans_course";
		ActConn enrConn = new ActConn(theArgs);
		ArrayList<String> enrollments = StringParsing.CourseParse(enrConn.makeTheConnection());
		int q = 1;
		for(String i : enrollments) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void TransStudentBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from stu_enroll";
		ActConn enrConn = new ActConn(theArgs);
		ArrayList<String> enrollments = StringParsing.CourseParse(enrConn.makeTheConnection());
		int q = 1;
		for(String i : enrollments) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%5==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
}
