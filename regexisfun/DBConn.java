import java.util.Scanner;

public class DBConn{
	static String[] theArgs;
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		System.out.println("Please state your purpose (login,query");
		theArgs[0] = cin.nextLine();
		System.out.println("Please enter a query statement:");
		theArgs[1] = cin.nextLine();
		ActConn myconn = new ActConn(theArgs);
	}
}