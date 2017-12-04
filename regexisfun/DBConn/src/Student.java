import java.util.ArrayList;

public class Student {
	long number;
	String firstName;
	String lastName;
	String id;
	String email;
	String degreePlan;
	
	public Student(ArrayList<String> theMoney){
		number = Long.parseLong(theMoney.get(0));
		firstName = theMoney.get(1);
		lastName = theMoney.get(2);
		id = theMoney.get(3);
		email = theMoney.get(5);
		degreePlan = theMoney.get(6);
	}
	
	public long getNumber() {return number;}
	public void setNumber(long number) {this.number = number;}
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getDegreePlan() {return degreePlan;}
	public void setDegreePlan(String degreePlan) {this.degreePlan = degreePlan;}
	
	public String toString(){
		return "Student : "+firstName+" "+lastName+", # "+number+"\nID: "+id+", Email: "+email+"  Currently in "+degreePlan+" degree plan";
	}	
}