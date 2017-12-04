import java.util.Scanner;
import java.util.ArrayList;

public class DBConn{
	static String stuEmail;
	static String[] theArgs = new String[3];
	static Student targetStudent;
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Enter student email to login");
		stuEmail = cin.nextLine();
		ArrayList<Student> studentList = StudentBiz(stuEmail);
		if(studentList.get(0)!=null) {
			targetStudent = studentList.get(0);
			System.out.println(targetStudent);
		}
		else
			System.out.println("No students found by that information");
		ArrayList<DegreeRequirements> degReq = Rq_ln_courseBiz();
		ArrayList<DegreeRequirements> degReqMin = DegReqMin(degReq);
		int totalCredits = 0;
		for(DegreeRequirements i : degReqMin){
			System.out.println(i);
			totalCredits+=i.getCredits();
		}
		System.out.println(totalCredits + " required for degree.");
		//runTheGambit();
		
	}
	
	public static void runTheGambit(){
		System.out.println("Students");
		ArrayList<Student> studentList = StudentBiz(stuEmail);
		SpaceBetween();
		System.out.println("Classes");
		ClassBiz();
		SpaceBetween();
		System.out.println("Courses");
		CourseBiz();
		SpaceBetween();
		System.out.println("Degrees");
		DegreeBiz();
		SpaceBetween();
		System.out.println("Departments");
		DepartmentBiz();
		SpaceBetween();
		System.out.println("Employees");
		EmployeeBiz();
		SpaceBetween();
		System.out.println("Log Files");
		LogFileBiz();
		SpaceBetween();
		System.out.println("Requirements");
		RequirementBiz();
		SpaceBetween();
		System.out.println("RQ LN Course?");
		Rq_ln_courseBiz();
		SpaceBetween();
		System.out.println("RQ LN Transcripts?");
		Rq_ln_transcBiz();
		SpaceBetween();
		System.out.println("Student Enrollments");
		Stu_enrollBiz();
		SpaceBetween();
		System.out.println("Transcripts");
		TransCourseBiz();
		SpaceBetween();
		System.out.println("Student Transcripts?");
		TransStudentBiz();
		SpaceBetween();
		ListTables();
		for(Student i : studentList){
			System.out.println(i.toString());
		}
	}
	
	public static void SpaceBetween(){
		for(int x = 0; x < 20; x++)
			System.out.print("-");
		System.out.println("");
	}
	
	private static ArrayList<Student> StudentBiz(String inputEmail){
		theArgs[0] = "query";
		theArgs[1] = "select * from student where email = '"+inputEmail+"'";
		theArgs[2] = null;
		ActConn stuConn = new ActConn(theArgs);
		ArrayList<String> students = StringParsing.StudentParse(stuConn.makeTheConnection());
		ArrayList<Student> studentList = new ArrayList<>();
		ArrayList<String> buildStuList = new ArrayList<>();
		int q = 1;
		for(String i : students){
			buildStuList.add(i);
			if(q%7==0){
				studentList.add(new Student(buildStuList));
				buildStuList.clear();
			}
			q++;
		}
		return studentList;
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
		theArgs[1] = "select course_code, name from course";
		ActConn couConn = new ActConn(theArgs);
		ArrayList<String> courses = StringParsing.CourseParse(couConn.makeTheConnection());
		int q = 1;
		for(String i : courses){
			System.out.print(i);
			if(q%2==0)
				System.out.println(" ");
			else
				System.out.print(" : ");
			q++;
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
		ArrayList<String> degrees = StringParsing.CourselessParse(degConn.makeTheConnection());
		int q = 1;
		for(String i : degrees) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%2==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void DepartmentBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from department";
		ActConn depConn = new ActConn(theArgs);
		ArrayList<String> departments = StringParsing.DepartmentsParse(depConn.makeTheConnection());
		int q = 1;
		for(String i : departments) {
			if(q%2==0){
				System.out.print(i);
				System.out.print(" , ");
			}
			if(q%6==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void EmployeeBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from employee";
		ActConn empConn = new ActConn(theArgs);
		ArrayList<String> employees = StringParsing.EmployeesParse(empConn.makeTheConnection());
		int q = 1;
		for(String i : employees) {
			if(q%2==0&&((q-8)%10)!=0){
				System.out.print(i);
				System.out.print(" , ");
			}
			if(q%10==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void LogFileBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from logfile";
		ActConn logConn = new ActConn(theArgs);
		ArrayList<String> logFiles = StringParsing.CourselessParse(logConn.makeTheConnection());
		int q = 1;
		for(String i : logFiles) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%2==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void RequirementBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from requirement";
		ActConn reqConn = new ActConn(theArgs);
		ArrayList<String> requirements = StringParsing.RequirementParse(reqConn.makeTheConnection());
		int q = 1;
		for(String i : requirements) {
			if(q%2==0){
				i = i.substring(1, i.length()-1);
				System.out.print(i);
				System.out.print(" , ");
			}
			if(q%6==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static ArrayList<DegreeRequirements> Rq_ln_courseBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from rq_ln_course";
		ArrayList<String> rqlnArray = new ArrayList<>();
		ArrayList<DegreeRequirements> degReq = new ArrayList<>();
		ActConn rqlnConn = new ActConn(theArgs);
		ArrayList<String> rqlns = StringParsing.RqlndParse(rqlnConn.makeTheConnection());
		int q = 1;
		for(String i : rqlns) {
			if(q%2==0){
				rqlnArray.add(i);
			}
			if(q%6==0) {
				degReq.add(new DegreeRequirements(rqlnArray));
				rqlnArray.clear();
			}
			q++;
		}
//		degReq = DegReqMin(degReq);
		return degReq;
	}
	
	private static ArrayList<DegreeRequirements> DegReqMin(ArrayList<DegreeRequirements> input){
		ArrayList<DegreeRequirements> output = new ArrayList<>();
		int q = 0;
		output.add(input.get(0));
		for(DegreeRequirements i : input) {
			if(output.get(0)!=i&&!input.get(q).getRqName().equals(input.get(q-1).getRqName())) {
				output.add(input.get(q));
			}
			q++;
		}
		return output;
	}
	
	private static void Rq_ln_transcBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from rq_ln_transc";
		ActConn rqlnConn = new ActConn(theArgs);
		ArrayList<String> rqlns = StringParsing.CourselessParse(rqlnConn.makeTheConnection());
		int q = 1;
		for(String i : rqlns) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%3==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void Stu_enrollBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from stu_enroll";
		ActConn enrConn = new ActConn(theArgs);
		ArrayList<String> enrollments = StringParsing.CourselessParse(enrConn.makeTheConnection());
		int q = 1;
		for(String i : enrollments) {
			if(!i.equals("1")&&!i.equals("001")){
				System.out.print(i);
				System.out.print(" , ");
				if(q%4==0)
					System.out.println("");
				q++;
			}
		}
		System.out.println("");
	}
	
	private static void TransCourseBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from trans_course";
		ActConn enrConn = new ActConn(theArgs);
		ArrayList<String> enrollments = StringParsing.CourselessParse(enrConn.makeTheConnection());
		int q = 1;
		for(String i : enrollments) {
			System.out.print(i);
			System.out.print(" , ");
			if(q%4==0)
				System.out.println("");
			q++;
		}
		System.out.println("");
	}
	
	private static void TransStudentBiz() {
		theArgs[0] = "query";
		theArgs[1] = "select * from trans_stu";
		ActConn enrConn = new ActConn(theArgs);
		ArrayList<String> enrollments = StringParsing.CourselessParse(enrConn.makeTheConnection());
		int q = 1;
		for(String i : enrollments) {
			if(!i.equals("1")&&!i.equals("001")){
				System.out.print(i);
				System.out.print(" , ");
				if(q%3==0)
					System.out.println("");
				q++;
			}
		}
		System.out.println("");
	}
}