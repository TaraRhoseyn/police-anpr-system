// TODO: import only packages actually used
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
    // init arraylist
    public static ArrayList<ArrayList<String>> carsInMemory = new ArrayList<ArrayList<String> >();
    // method to add arraylist to 2d arraylist
    public static ArrayList<ArrayList<String>> addCarToMemory() {
        // user input of car details
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new car VRN to daily log:");
        String VRN = scanner.nextLine();
        System.out.println("Add today's date:");
        String date = scanner.nextLine();
        System.out.println("Add the time:");
        String time = scanner.nextLine();
        carsInMemory.add(new ArrayList<String>(Arrays.asList(VRN, date, time)));
        System.out.println(hashes+"\nCar added to memory."+
            "\nPlease select an option:\n"+
            "1 -- Add another car\n"+
            "2 -- View all cars inputted\n"+
            "3 -- Save all cars to a daily log file\n"+
            "4 -- Exit\n");
        int shiftOptions = scanner.nextInt();
        switch(shiftOptions) {
            case 1:
                addCarToMemory();
                break;
            case 2:
                System.out.println(hashes+"\nAll cars recorded today:\n");
                // iterates through arraylist rows and prints each:
                Iterator itr = carsInMemory.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
                System.out.println(hashes+"\nPlease select an option.\n"+
                    "1 -- Add another car\n"+
                    "2 -- Save all cars to a daily log file\n"+
                    "3 -- Exit");
                int viewOptions = scanner.nextInt();
                switch(viewOptions) {
                    case 1:
                        addCarToMemory();
                        break;
                    case 2:
                        addCarsToFile(carsInMemory);
                        break;
                    case 3:
                        return carsInMemory;
                } 
                break;
            case 3:
                addCarsToFile(carsInMemory);
            case 4:
                return carsInMemory;
        }
        // return statement. must go at end. do i actually need this return value?
        return carsInMemory;
    }
    // TODO: distribute out logic

    public static void addCarsToFile(ArrayList<ArrayList<String>> carsInMemory) {
        // Creates new file of all the logged cars during a daily operator shift
        try {
            FileWriter file = new FileWriter("daily_shift_log.csv");
            file.write("-- Daily shift log --\n");
            for(int i=0; i<carsInMemory.size(); i++) {  // iterates through row
                for(int j=0; j<carsInMemory.get(i).size(); j++) {   // iterates through col
                    String colValue = carsInMemory.get(i).get(j);
                    String row = String.format("%s,", colValue);
                    // FIX: final comma appearing after third data
                    file.append(row);
                }
                file.append("\n");
            }
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        // general info:
        System.out.println("\nNORTH YORKSHIRE POLICE: ANPR SYSTEM");
        System.out.println(hashes+"\n");
        // init scanner:
        Scanner scanner = new Scanner(System.in);
        // start shift
        System.out.println("Begin shift? Please type and enter for a response.\n1 -- Yes\n2 -- No"+"\n"+hashes);
        int shift = scanner.nextInt();
        if (shift == 1) {
            addCarToMemory();   
        } else {
            System.out.println("Shift not begun. Application terminated.");
        }
    }
}