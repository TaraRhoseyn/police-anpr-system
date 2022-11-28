// TODO: import only packages actually used
import java.util.*;
import java.io.*;

public class App {

    public static void main(String[] args) throws Exception {
        // general info:
        System.out.println("\nNORTH YORKSHIRE POLICE: ANPR SYSTEM");
        // init scanner:
        Scanner sc = new Scanner(System.in);
        // start shift
        System.out.println("MENU\n1 -- Add car to memory\n2 -- Edit car in memory"+"\n");
        int select = sc.nextInt();
        sc.nextLine();
        if (select == 1) {
            Vehicle vehicle = new Vehicle();
            System.out.println("Please enter VRN:");
            String VRN = sc.nextLine();
            vehicle.setVRN(VRN);
            System.out.println("Please enter date:");
            String date = sc.nextLine();
            vehicle.setDate(date);
            System.out.println("Please enter date:");
            String time = sc.nextLine();
            vehicle.setTime(time);
            System.out.println("Object created:");
            System.out.println(vehicle.getVRN());
            System.out.println(vehicle.getDate());
            System.out.println(vehicle.getTime());
        } else if (select == 2) {
            
        }
    }
}