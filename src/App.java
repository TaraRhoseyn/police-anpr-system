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
    public static ArrayList<ArrayList<String>> carsInMemory = new ArrayList<ArrayList<String>>();
    public static void viewCarDetails(ArrayList<ArrayList<String>> multiArrlist) {
        /*
        TRIED OVERLOADING METHOD
        to include 1D arrlist but would throw exceptions
        ??


        Method that prints out multidimensional ArrayLists,
        used for printing camera data held in memory
        and data from PNC file
        */
        Iterator itr = multiArrlist.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
    public static void checkVRN(String VRN) {
        /*
        Method that takes the user-inputted VRN,
        checks the VRN against the VRNs in the PNC file,
        gives warning to user if match is found,
        logs match to daily log file
        */
        try {
            File PNCFile = new File("vehicles_of_interest.csv");
            ArrayList<ArrayList<String>> PNC = new ArrayList<ArrayList<String>>();
            Scanner scannedPNCFile = new Scanner(PNCFile);
            while (scannedPNCFile.hasNextLine()) {
                String line = scannedPNCFile.nextLine();
                String[] values = line.split(",");
                PNC.add(new ArrayList<String>(Arrays.asList(values)));
            };
            scannedPNCFile.close();
            for(int i=0; i<PNC.size(); i++) { 
                String VRNfromPNC = PNC.get(i).get(0);
                if (VRNfromPNC.equalsIgnoreCase(VRN)) {
                    System.out.println(hashes+"\nMATCH FOUND!\n"+hashes);
                    System.out.println("The car you have logged matches a car in the PNC.");
                    System.out.println("The vehicle of interest is:\n");
                    System.out.println(String.join(", ", PNC.get(i)));
                }
            }
            /*
            TODO: problem:: if i send this matched info
            to log file now, it could be incorrect if user
            then edits a VRN in memory
             */
        } catch (Exception e) {
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
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
        checkVRN(VRN);
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
                viewCarDetails(carsInMemory);
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
    public static void editCarInMemory(ArrayList<ArrayList<String>> carsInMemory) {
        /*
        Method that updates all elements
        of an inner list (selected by user) of multidimensional ArrayList
        that stores the car information captured by camera
        */
        System.out.println("You have chosen to edit a car entry.\n"+
        "Please chose which entry you wish to change:");
        Iterator itr = carsInMemory.iterator();
        int i=0,j=1; // 'i' relfects true index for iterating, 'j' is for the user's benefit so the first option isn't 0
        while (itr.hasNext()) {
            System.out.println("Number "+j+": "+itr.next());
            i++;
            j++;
        }
        Scanner scan = new Scanner(System.in);
        j = scan.nextInt();
        j=j-1; // Sets user selected number to true index
        scan.nextLine(); // Fixes Scanner bug between nextInt and nextLine methods
        System.out.println("Add new car VRN to daily log:");
        String VRN = scan.nextLine();
        System.out.println("Add today's date:");
        String date = scan.nextLine();
        System.out.println("Add the time:");
        String time = scan.nextLine();
        checkVRN(VRN);
        carsInMemory.get(j).set(0, VRN);
        carsInMemory.get(j).set(1, date);
        carsInMemory.get(j).set(2, time);
        System.out.println(hashes);
        System.out.println("Edit successfully saved. Updated car log:\n");
        viewCarDetails(carsInMemory);
    }
    public static void addCarsToFile(ArrayList<ArrayList<String>> carsInMemory) {
        // Creates new file of all shift activity, including VRNs logged and any matches with PNC found
        try {
            FileWriter file = new FileWriter("daily_shift_log.csv");
            file.append("---- DAILY LOG FILE ----\n");
            file.append("---- VRNs SCANNED: ----\n");
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