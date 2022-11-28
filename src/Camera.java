// TODO: import only packages actually used
import java.util.*;
import java.io.*;

class Camera {
    // UTILITIES
    String hashes = "------------------------------";
    final int count = 0;
    // class attributes
    ArrayList<ArrayList<String>> carsInMemory = new ArrayList<ArrayList<String>>();
    // start menu:
    void displayStartMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println(hashes+"North Yorkshire ANPR System"+hashes);
        System.out.println("\nPlease select an option:"+
            "\n 1 - Add a vehicle"+ // logic done
            "\n 2 - View all vehicles"+ // logic done
            "\n 3 - Amend a vehicle"+ // logic done
            "\n 4 - Remove a vehicle"+ // logic done
            "\n 5 - End shift (save to log file)"+ // logic done
            "\n 6 - (Admin) View PNC file"+
            "\n 7 - (Admin) Amend a vehicle in PNC file"+
            "\n 8 - (Admin) Remove a vehicle in PNC file"+
            "\n 9 - (Admin) View daily shift log");
        int selector = scan.nextInt();
        switch(selector) {
            case 1:
                addCar();
                displayStartMenu();
                break;
            case 2:
                viewCar(carsInMemory);
                displayStartMenu();
                break;
            case 3:
                amendCar(carsInMemory);
                displayStartMenu();
                break;
            case 4:
                removeCar(carsInMemory);
                displayStartMenu();
                break;
            case 5:
                saveCarsToLogFile(carsInMemory);
                displayStartMenu();
                break;
        }
    }
    ArrayList<ArrayList<String>> addCar() {
        // user input of car details
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new car VRN to daily log:");
        String VRN = scanner.nextLine();
        System.out.println("Add today's date:");
        String date = scanner.nextLine();
        System.out.println("Add the time:");
        String time = scanner.nextLine();
        // checks VRN against PNC:
        checkVRNwithPNC(VRN);
        carsInMemory.add(new ArrayList<String>(Arrays.asList(VRN, date, time)));
        System.out.println(hashes+"\nCar added.\nPlease select an option.\n1 - Return to Menu\n2 - Exit (save to shift log)");
        int selector = scanner.nextInt();
        switch(selector) {
            case 1:
                displayStartMenu();
                break;
            case 2:
                saveCarsToLogFile(carsInMemory);
                break;
        }
        return carsInMemory;
    }
    void viewCar(ArrayList<ArrayList<String>> arrlist) {
        /* Method that uses a lambda expression to return and print a new String from
        a multidimensional ArrayList */
        arrlist.forEach((c)->{System.out.println(String.join(", ",c));});
    }
    void checkVRNwithPNC(String VRN) {
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
    void removeCar(ArrayList<ArrayList<String>> arrlist) {
        System.out.println("You have chosen to remove a car entry.\n"+
        "Please chose which entry you wish to remove:");
        Iterator itr = arrlist.iterator();
        int i=0,j=1; // 'i' relfects true index for iterating, 'j' is for the user's benefit so the first option isn't 0
        while (itr.hasNext()) {
            System.out.println("Number "+j+": "+itr.next());
            i++;
            j++;
        }
        Scanner scan = new Scanner(System.in);
        j = scan.nextInt();
        j=j-1;
        arrlist.remove(j);
        System.out.println(hashes);
        System.out.println("Car successfully removed. Updated car log:\n");
        viewCar(arrlist);
    }
    ArrayList<ArrayList<String>> amendCar(ArrayList<ArrayList<String>> carsInMemory) {
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
        checkVRNwithPNC(VRN);
        carsInMemory.get(j).set(0, VRN);
        carsInMemory.get(j).set(1, date);
        carsInMemory.get(j).set(2, time);
        System.out.println(hashes);
        System.out.println("Edit successfully saved. Updated car log:\n");
        viewCar(carsInMemory);
        return carsInMemory;
    }
    void saveCarsToLogFile(ArrayList<ArrayList<String>> carsInMemory) {
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
}