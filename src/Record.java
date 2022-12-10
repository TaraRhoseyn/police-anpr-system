// TODO: import only packages actually used
import java.util.*;
import java.io.*;

class Record {
    // UTILITIES
    String hashes = "------------------------------";
    final int count = 0;
    // class attributes
    ArrayList<ArrayList<String>> vehiclesArrlist = new ArrayList<ArrayList<String>>();
    // start menu:
    void displayStartMenu() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        Admin admin = new Admin();
        System.out.println(hashes+"North Yorkshire ANPR System"+hashes);
        System.out.println("\nPlease select an option:"+
            "\n 1 - Add a vehicle"+ // logic done
            "\n 2 - View all vehicles"+ // logic done
            "\n 3 - Amend a vehicle"+ // logic done
            "\n 4 - Remove a vehicle"+ // logic done
            "\n 5 - End shift (save to log file)"+ // logic done
            "\n 6 - (Admin) View PNC file"+ // logic done
            "\n 7 - (Admin) Amend a vehicle in PNC file"+ 
            "\n 8 - (Admin) Remove a vehicle in PNC file"+ // logic done but needs fixes
            "\n 9 - (Admin) View daily shift log"+
            "\n10 - Exit application");
        int selector = scan.nextInt();
        switch(selector) {
            case 1:
                addVehicle();
                displayStartMenu();
                break;
            case 2:
                viewVehicles(vehiclesArrlist);
                displayStartMenu();
                break;
            case 3:
                amendVehicle(vehiclesArrlist);
                displayStartMenu();
                break;
            case 4:
                removeVehicle(vehiclesArrlist);
                displayStartMenu();
                break;
            case 5:
                saveCarsToLogFile(vehiclesArrlist);
                displayStartMenu();
                break;
            case 6:
                ArrayList<ArrayList<String>> readFile = admin.readPNCfile();
                admin.viewPNCfile(readFile);
                displayStartMenu();
            case 8:
                admin.removePNCvehicle();
                displayStartMenu();
        }
    }
    ArrayList<ArrayList<String>> addVehicle() {
        // user input of car details
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a Vehicle Registration Number:");
        String VRNinput = scanner.nextLine();
        Vehicle vehicle = new Vehicle(VRNinput);
        String VRN = vehicle.getVRN();
        String date = vehicle.getDate();
        String time = vehicle.getTime();
        // checks VRN against PNC:
        checkVRNwithPNC(VRN);
        vehiclesArrlist.add(new ArrayList<String>(Arrays.asList(VRN, date, time)));
        System.out.println(hashes+"\nCar added.\nPlease select an option.\n1 - Return to Menu\n2 - Exit (save to shift log)");
        int selector = scanner.nextInt();
        switch(selector){
            case 1:
                try {
                    displayStartMenu();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                saveCarsToLogFile(vehiclesArrlist);
                break;
        }
        return vehiclesArrlist;
    }
    void viewVehicles(ArrayList<ArrayList<String>> arrlist) {
        /* Method that uses a lambda expression to return and print a new String from
        each inner list of a multidimensional ArrayList */
        arrlist.forEach((c)->{System.out.println(String.join(", ",c));});
    }
    void checkVRNwithPNC(String VRN) {
        /*
        Method that takes the user-inputted VRN,
        checks the VRN against the VRNs in the PNC file,
        gives warning to user if match is found
        */
        try {
            Admin admin = new Admin();
            ArrayList<ArrayList<String>> readFile = admin.readPNCfile();
            for(int i=0; i<readFile.size(); i++){ 
                String VRNfromPNC = readFile.get(i).get(0);
                if(VRNfromPNC.equalsIgnoreCase(VRN)){
                    System.out.println(hashes+"\nMATCH FOUND!\n"+hashes);
                    System.out.println("The car you have logged matches a car in the PNC.");
                    System.out.println("The vehicle of interest is:\n");
                    System.out.println(String.join(", ", readFile.get(i)));
                }
            }
            /*
            TODO:
            Add functionality of adding matches to daily_shift_log... so only trigger method at end of shift?
             */
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
    }
    void removeVehicle(ArrayList<ArrayList<String>> arrlist) {
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
        System.out.println("Car successfully removed. Updated list:\n");
        viewVehicles(arrlist);
    }
    ArrayList<ArrayList<String>> amendVehicle(ArrayList<ArrayList<String>> vehiclesArrlist) {
        /*
        Method that updates all elements
        of an inner list (selected by user) of multidimensional ArrayList
        that stores the car information captured by camera
        */
        System.out.println("You have chosen to edit a car entry.\n"+
        "Please chose which entry you wish to change:");
        Iterator itr = vehiclesArrlist.iterator();
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
        vehiclesArrlist.get(j).set(0, VRN);
        vehiclesArrlist.get(j).set(1, date);
        vehiclesArrlist.get(j).set(2, time);
        System.out.println(hashes);
        System.out.println("Edit successfully saved. Updated car log:\n");
        viewVehicles(vehiclesArrlist);
        return vehiclesArrlist;
    }
    void saveCarsToLogFile(ArrayList<ArrayList<String>> vehiclesArrlist) {
        // Creates new file of all shift activity, including VRNs logged and any matches with PNC found
        try {
            FileWriter file = new FileWriter("daily_shift_log.csv");
            file.append("---- DAILY LOG FILE ----\n");
            file.append("---- VRNs SCANNED: ----\n");
            for(int i=0; i<vehiclesArrlist.size(); i++) {  // iterates through row
                for(int j=0; j<vehiclesArrlist.get(i).size(); j++) {   // iterates through col
                    String colValue = vehiclesArrlist.get(i).get(j);
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