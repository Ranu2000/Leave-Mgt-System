import java.util.Scanner;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.*;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;

public class Main {
//    public static String textFName;
//    public static String textLName;
//    public static String textAddress;
//    public static String textMemberID;

    public static MongoClient mongoClient = new MongoClient("localhost", 27017);
    public static MongoDatabase database = mongoClient.getDatabase("LeaveDataBase");
    public static MongoCollection<Document> collection = database.getCollection("Member");

    public static void main(String[] args) {

         String searchFirstName;

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\nPress 1: If you are an Employee");
            System.out.println("Press 2: If you are the Manager");
            System.out.println("Press 0: To leave");
            System.out.print("Choose a number\n");
            int num = scanner.nextInt();

            if (num==1){
                System.out.println("You are in employee category");
                System.out.println("1 - Request a leave");
                System.out.println("2 - See your leave balance");
                int empNum = scanner.nextInt();

                if (empNum==1){
//                    System.out.println("save in mongo db");
                    Employee requestLeave = new Employee();
                    requestLeave.setfName();
                    requestLeave.seteId();
                    requestLeave.setLeaveType();

                    Document document = new Document();
                    document.put("employeeId",requestLeave.geteId());
                    document.put("Name",requestLeave.getfName());
                    document.put("Type",requestLeave.getLeaveType());

                    collection.insertOne(document);
                }
                else if (empNum==2){
                    MongoCollection<Document> appCollection = database.getCollection("approveMember");
                    System.out.println("show leave balance");
                    System.out.print("Enter your employee ID:");
                    int shNum = scanner.nextInt();

                 for (Document dc : appCollection.find(eq("employeeId", shNum))){
                     System.out.println("\n"+dc);
                 }


//                    for (Document dc : appCollection.find()) {
//                        System.out.println(dc);
//                    }

                }
            }

            else if (num==2){
                System.out.println("You are in Manager");

                while (true){
                    System.out.println("Enter the password");
                    int password = scanner.nextInt();

                    if (password==1234){
                        while (true){
                            System.out.println("Details of requests\n");
                            for (Document document : collection.find()) {
                                System.out.println(document);
                            }

                            System.out.println("\npress 1: To approve");
                            System.out.println("press 0: To Exit ");
                            System.out.println("Choose a number");
                            int chNum = scanner.nextInt();


                            if (chNum==1){
                                System.out.println("enter the id");
                                int Num = scanner.nextInt();
                                MongoCollection<Document> appCollection = database.getCollection("approveMember");

                                BasicDBObject query = new BasicDBObject();
                                query.put("employeeId", Num);

                                collection.deleteOne(query);
                                System.out.println("Employees's leave approved.");

                                Document dc = new Document();
                                dc.put("employeeId",Num);
//                        dc.put("Type",);
                                appCollection.insertOne(dc);
                                System.out.println("\n");

                            }
                            else if (chNum==0){
                                return;
                            }


                        }

                    }else {
                        System.out.println("Wrong password, try again");
                    }
                }

//                while (true){
//                    for (Document document : collection.find()) {
//                        System.out.println(document);
//                    }
//                    System.out.println("Choose a number");
//                    System.out.println("press 1 to approve");
//                    System.out.println("press 2 to ");
//                    int chNum = scanner.nextInt();
//
//                    if (chNum==1){
//                        System.out.println("enter the id");
//                        int Num = scanner.nextInt();
//                        MongoCollection<Document> appCollection = database.getCollection("approveMember");
//
//                        BasicDBObject query = new BasicDBObject();
//                        query.put("employeeId", Num);
//
//                        collection.deleteOne(query);
//                        System.out.println("Member details successfully removed.");
//
//                        Document dc = new Document();
//                        dc.put("employeeId",Num);
////                        dc.put("Type",);
//                        appCollection.insertOne(dc);
//                        System.out.println("\n\n\n\n");
//
//                    }
//
//
//                }




//                Document document = new Document("LeaveDataBase","Member");
//                document.append("Name", "employeeId");


//                StringBuilder text = new StringBuilder();
//                for (Document document : collection.find()) {
//                    text.append(document.get("employeeId")).append("\n");
//                }
//                textMemberID = String.valueOf(text);
//
//                text = new StringBuilder();
//                for (Document document : collection.find()) {
//                    text.append(document.get("Name")).append("\n");
//                }
//                textFName = String.valueOf(text);


//                GUI.main(args);

            }

            else if (num==0){
                break;
            }
        }
    }
}
