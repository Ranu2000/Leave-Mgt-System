import java.util.Scanner;

public class Employee {

    Scanner sc = new Scanner(System.in);

    int eId;
    String fName;
    String leaveType;


    public String getfName() {
        return fName;
    }

    public void setfName() {
        System.out.println("Enter your name");
        this.fName = sc.next();
    }

    public int geteId() {
        return eId;
    }

    public void seteId() {
        System.out.println("Enter Employee Id");
        this.eId = sc.nextInt();
    }

    public String getLeaveType(){
        return leaveType;
    }

    public void setLeaveType(){
        System.out.println("Enter the leave type");
        this.leaveType = sc.next();
    }

    public String writeData(){
        return ("-------------------------------\n" +
                "Member ID : " + getfName() + "\n" +
                "First Name : " + geteId() + "\n");
//                "Last Name : " + getStudentLName() + "\n" +
//                "Address : " + getStudentAddress() + "\n" +
//                "Starting Date : " + getDefaultStartingDate() + "\n");
    }
}
