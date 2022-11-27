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
    // init as a class attribute arraylist
    public static ArrayList<ArrayList<String>> carsInMemory = new ArrayList<ArrayList<String> >();
    public static void checkVRN(ArrayList<ArrayList<String>> carsInMemory) {
        // method to check logged VRN against PNC file
    }
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
        // checkVRN(carsInMemory);
        System.out.println(hashes+"\nCar added to memory."+
            "\nPlease select an option:\n"+
            "1 -- Add another car\n"+
            "2 -- View all cars inputted\n"+
            "3 -- Save all cars to a daily log file\n"+
            "4 -- Exit\n"+
            "5 -- edit a car");
        int shiftOptions = scanner.nextInt();
        switch(shiftOptions) {
            case 1:
                addCarToMemory();
                break;
            case 2:
                System.out.println(hashes+"\nAll cars recorded today:\n");
                // iterates through arraylist rows and prints each:
                printCarsInMemory(carsInMemory);
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
            case 5:
                editCarInMemory(carsInMemory);
        }
        return carsInMemory;
    }
    public static void printCarsInMemory(ArrayList<ArrayList<String>> carsInMemory) {
        System.out.println("CARS IN MEMORY:\n");
        Iterator itr = carsInMemory.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
    public static void editCarInMemory(ArrayList<ArrayList<String>> carsInMemory) {
        // does this method need to return carsInMemory??
        
        // select which entry to edit:
        System.out.println("You have chosen to edit a car entry.\n"+
        "Please chose which entry you wish to change:");
        Iterator itr = carsInMemory.iterator();
        int i=0,j=1; // 'i' relfects true indice, 'j' is for the user's benefit so the first option isn't 0
        while (itr.hasNext()) {
            System.out.println("Number "+j+": "+itr.next());
            i++;
            j++;
        }
        Scanner scan = new Scanner(System.in);
        j = scan.nextInt();
        j=j-1; // Resets user selection to true indice
        scan.nextLine(); // Fixes scanner bug between nextInt and nextLine methods
        System.out.println("The value of the 'j' variable: "+j); // Just checks indice is correct
        System.out.println("Add new car VRN to daily log:");
        String VRN = scan.nextLine();
        System.out.println("Add today's date:");
        String date = scan.nextLine();
        System.out.println("Add the time:");
        String time = scan.nextLine();
        carsInMemory.get(j).set(0, VRN);
        carsInMemory.get(j).set(1, date);
        carsInMemory.get(j).set(2, time);
        System.out.println(hashes);
        System.out.println("Edit successfully saved. Updated car log:\n");
        printCarsInMemory(carsInMemory); // checks it's updated
    }
    public static void addCarsToFile(ArrayList<ArrayList<String>> carsInMemory) {
        // Creates new file of all shift activity, including VRNs logged and any matches with PNC found
        try {
            FileWriter file = new FileWriter("daily_shift_log.csv");
            file.append("---- DAILY LOG FILE ----\n");
            file.append("- VRNs logged: -\n");
            for(int i=0; i<carsInMemory.size(); i++) {  // iterates through row
                for(int j=0; j<carsInMemory.get(i).size(); j++) {   // iterates through col
                    String colValue = carsInMemory.get(i).get(j);
                    String row = String.format("%s,", colValue);
                    // FIX: final comma appearing after third piece of data in row
                    file.append(row);
                }
                file.append("\n");
            }
            file.close();
            // TODO: add functionality to log whether any PNC matches have been found during shift
        } catch (IOException e) {
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("\nA general error (not related to the file handling) has occurred. Please see error message:\n");
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