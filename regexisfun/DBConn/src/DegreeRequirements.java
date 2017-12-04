import java.util.ArrayList;

public class DegreeRequirements {
	int rqNum;
	String rqName;
	String rqClassCode;
	
	public DegreeRequirements(ArrayList<String> input) {
		rqNum = Integer.parseInt(input.get(0).substring(3, input.get(0).length()));
		rqName = input.get(1);
		rqClassCode = input.get(2);
	}
	
	// Getters and Setters
	public int getRqNum() {return rqNum;}
	public void setRqNum(int rqNum) {this.rqNum = rqNum;}
	public String getRqName() {return rqName;}
	public void setRqName(String rqName) {this.rqName = rqName;}
	public String getRqClassCode() {return rqClassCode;}
	public void setRqClassCode(String rqClassCode) {this.rqClassCode = rqClassCode;}
	
	public String toString() {
		return " "+rqNum+" , "+rqName + " , " +rqClassCode;
	}
}
