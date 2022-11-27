import java.util.*;
import java.io.*;

public class App {
    // policeofficer class
    public class PoliceOfficer {
        private int staffNum;
    }
    // adminofficer class
    public class AdminOfficer {
        
    }
    // vehicle Class
    public class Vehicle {
        /*
        Class to store all vehicle information
        including 
        */
        // class attributes
        // public String VRN;
        // public String make;
        // public String model;
        // public int yearOfManufacture;
        // public String colour;
        // public String information;
        // // methods
        // public void setVRN(String VRNinput) {
        //     try {
        //         /* Safety feature to ensure no spaces are in VRN that would compromise 
        //         the comparison between input and PNC data file */
        //         VRN = VRNinput.replaceAll(" ", "");
        //         if (VRN.length() > 10) {
        //             System.out.println("VRN is too long");
        //         }
        //     } catch (Exception  e) {
        //         System.out.println(e);
        //     }
        // }
    }
    // camera class
    public class Camera {
        // private int operatorNum;
        // ArrayList: carsInMemory
    }

    // UTILITIES
    public static String hashes = "------------------------------";
    public static int rowCounter = 0;
    public static void addCarToMemory() {
        // init arraylist
        ArrayList<ArrayList<String>> carsInMemory = new ArrayList<ArrayList<String> >();
        // user input of car details
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new car VRN to daily log:");
        String VRN = scanner.nextLine();
        System.out.println("Add today's date:");
        String date = scanner.nextLine();
        System.out.println("Add the time:");
        String time = scanner.nextLine();
        // adds single array of inputted values to the arraylist
        carsInMemory.add(new ArrayList<String>(Arrays.asList(VRN, date, time)));
        System.out.println(carsInMemory);
    }
    // TODO: distribute out logic
    public static void main(String[] args) throws Exception {
        // general info:
        System.out.println("\nNORTH YORKSHIRE POLICE: ANPR SYSTEM");
        System.out.println(hashes+"\n");
        // init scanner:
        Scanner scanner = new Scanner(System.in);
        // start shift
        System.out.println("Begin shift? Please type and enter for a response.\n1 -- YES\n2 -- NO"+"\n"+hashes);
        int shift = scanner.nextInt();
        if (shift == 1) {
            addCarToMemory();   
        } else {
            System.out.println("Shift not begun. Application terminated.");
        }
        System.out.println("Add another car?\n1 -- YES\n2-- NO");
        int addAnother = scanner.nextInt();
        if (addAnother == 1) {
            addCarToMemory();   
        } else {
            System.out.println("Shift not begun. Application terminated.");
        }
    }
}