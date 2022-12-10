import java.io.*;
import java.util.*;

public class Admin {
    
    public ArrayList<ArrayList<String>> readCSVfile(String filepath) throws FileNotFoundException {
        /**
        * This method reads a CSV file at the specified filepath and returns the
        * contents of the file as a multidimensional arraylist. The method uses the
        * Scanner class to read each line of the file and split it on the comma
        * character to create an inner arraylist of values. These inner arraylists are
        * added to the outer arraylist, which is then returned by the method. If an
        * error occurs while reading the file, the method prints an error message to
        * the terminal and returns an empty arraylist.
        */
        ArrayList<ArrayList<String>> arrlist = new ArrayList<ArrayList<String>>();
        try {
            File file = new File(filepath);
            Scanner scannedFile = new Scanner(file);
            while(scannedFile.hasNextLine()){
                String line = scannedFile.nextLine();
                String[] values = line.split(",");
                arrlist.add(new ArrayList<String>(Arrays.asList(values)));
            };
            scannedFile.close();
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
        return arrlist;
    }
    public void viewPNCfile(ArrayList<ArrayList<String>> arrlist) throws FileNotFoundException {
        /*
        * This method takes a multidimensional arraylist as an argument and uses it to display the entries in a CSV file. 
        * It first uses the viewVehicles method from the Record class to print the entries to the terminal. 
        * It then calls the displayStartMenu method from the Record class to display the start menu again. 
        * If an error occurs, it prints an error message to the terminal.
        */
        try {
            Record record = new Record();
            record.viewVehicles(arrlist);
            record.displayStartMenu();
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
    }
    public void viewDailyLogFile() throws FileNotFoundException{
        /**
        * This method reads the "daily_shift_log.csv" file using the readCSVfile method
        * and then prints the contents of the file to the terminal. The method first
        * creates an empty arraylist and then uses the readCSVfile method to populate
        * the arraylist with the contents of the CSV file. The method then iterates over
        * the arraylist and prints the contents of each inner list to the terminal.
        */
        ArrayList<ArrayList<String>> dailyLogArrlist = new ArrayList<ArrayList<String>>();
        dailyLogArrlist = readCSVfile("daily_shift_log.csv");
        // Iterate over the arraylist and print the contents of each inner list
        for (List<String> innerList : dailyLogArrlist) {
            for (String element : innerList) {
            System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public void amendPNCVehicle() throws FileNotFoundException {
        ArrayList<ArrayList<String>> CSVarrlist = new ArrayList<ArrayList<String>>();
        CSVarrlist = readCSVfile("vehicles_of_interest.csv");
        System.out.println("You have chosen to edit a car entry.\n"+
        "Please chose which entry you wish to change:");
        Iterator itr = CSVarrlist.iterator();
        int i=0,j=1; // 'i' relfects true index for iterating, 'j' is for the user's benefit so the first option isn't 0
        while (itr.hasNext()) {
            System.out.println("Number "+j+": "+itr.next());
            i++;
            j++;
        }
        Scanner scanner = new Scanner(System.in);
        j = scanner.nextInt();
        j=j-1; // Sets user selected number to true index
        scanner.nextLine(); // Fixes Scanner bug between nextInt and nextLine methods
        System.out.println("Add a new Vehicle Registration Number:");
        String VRNinput = scanner.nextLine();
        System.out.println("Please enter the vehicle's make:");
        String make = scanner.nextLine();
        System.out.println("Please enter the vehicle's model:");
        String model = scanner.nextLine();
        System.out.println("Please enter the vehicle's year of manufacture:");
        int year = 0;
        try {
            year = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input for year. Please try again.");
            scanner.nextLine(); // Consume the invalid input
            return; // Exit the method
        }
        String yearStr = Integer.toString(year);
        System.out.println("Please enter the vehicle's colour:");
        String col = scanner.nextLine();
        System.out.println("Please enter any information about the vehicle:");
        String info = scanner.nextLine();
        CSVarrlist.get(j).set(0, VRNinput);
        CSVarrlist.get(j).set(1, make);
        CSVarrlist.get(j).set(2, model);
        CSVarrlist.get(j).set(3, yearStr);
        CSVarrlist.get(j).set(4, col);
        CSVarrlist.get(j).set(5, info);
        try {
            File fileToDelete = new File("vehicles_of_interest.csv");
            if (fileToDelete.exists()){
                fileToDelete.delete();
            }
            FileWriter writer = new FileWriter("vehicles_of_interest.csv");
            // Iterate over the arraylist and write each inner list to the CSV file
            for (List<String> innerList : CSVarrlist) {
            for (String element : innerList) {
                writer.write(element + ",");
            }
            writer.write("\n");
            }
            writer.close();
            System.out.println("New car successfully added. Returning to menu...");
            Record rec = new Record();
            rec.displayStartMenu();
        } catch (FileNotFoundException e) {
            System.out.println("A file handling error has occurred. Please see the error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error has occurred:");
            e.printStackTrace();
        }
    }

    public void addPNCvehicle() throws FileNotFoundException{
        /*
        * This method adds a vehicle of interest to the vehicles_of_interest.csv file. 
        * It prompts the user to enter the vehicle registration number, make, 
        * model, year of manufacture, colour, and any other information about the vehicle. 
        * It then adds this information to the vehicles_of_interest.csv file, 
        * and prints a message to the user indicating that the car has been added. 
        * It also prints a list of all cars in the file to the user. 
        * If an error occurs while handling the file, the error is printed to the user. 
        * Finally, the method returns the user to the start menu.
        */
        ArrayList<ArrayList<String>> PNCvehs = new ArrayList<ArrayList<String>>();
        PNCvehs = readCSVfile("vehicles_of_interest.csv");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a Vehicle Registration Number:");
        String VRNinput = scanner.nextLine();
        System.out.println("Please enter the vehicle's make:");
        String make = scanner.nextLine();
        System.out.println("Please enter the vehicle's model:");
        String model = scanner.nextLine();
        System.out.println("Please enter the vehicle's year of manufacture:");
        int year = 0;
        try {
            year = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input for year. Please try again.");
            scanner.nextLine(); // Consume the invalid input
            return; // Exit the method
        }
        System.out.println("Please enter the vehicle's colour:");
        String col = scanner.nextLine();
        System.out.println("Please enter any information about the vehicle:");
        String info = scanner.nextLine();
        Vehicle veh = new Vehicle(VRNinput,make,model,year,col,info);
        String VRN = veh.getVRN();
        make = veh.getMake();
        model = veh.getModel();
        year = veh.getYear();
        String yearStr = Integer.toString(year);
        col = veh.getColour();
        info = veh.getInfo();
        PNCvehs.add(new ArrayList<String>(Arrays.asList(VRN, make, model, yearStr, col, info)));
        System.out.println("\nCar succesfully added.");
        for (List<String> innerList : PNCvehs) {
            for (String element : innerList) {
            System.out.print(element + " ");
            }
            System.out.println();
        }
        try {
            File fileToDelete = new File("vehicles_of_interest.csv");
            if (fileToDelete.exists()){
                fileToDelete.delete();
            }
            FileWriter writer = new FileWriter("vehicles_of_interest.csv");
            // Iterate over the arraylist and write each inner list to the CSV file
            for (List<String> innerList : PNCvehs) {
            for (String element : innerList) {
                writer.write(element + ",");
            }
            writer.write("\n");
            }
            writer.close();
            System.out.println("New car successfully added. Returning to menu...");
            Record rec = new Record();
            rec.displayStartMenu();
        } catch (FileNotFoundException e) {
            System.out.println("A file handling error has occurred. Please see the error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error has occurred:");
            e.printStackTrace();
        }
    }
    public void removePNCvehicle(){
        /*
        * This method removes an entry from a CSV file and updates the file with the remaining entries. 
        * It does this by first reading the CSV file into a multidimensional arraylist using the readCSVfile method. 
        * It then prompts the user to choose which entry they want to remove and removes it from the arraylist. 
        * Finally, it writes the updated arraylist back to the CSV file.
        */
        try {
            ArrayList<ArrayList<String>> arrlistPNC = readCSVfile("vehicles_of_interest.csv");
            System.out.println("You have chosen to remove a car entry.\n"+
            "Please chose which entry you wish to remove:");
            Iterator itr = arrlistPNC.iterator();
            int i=0,j=1; // 'i' relfects true index for iterating, 'j' is for the user's benefit so the first option isn't 0
            while (itr.hasNext()) {
                System.out.println("Number "+j+": "+itr.next());
                i++;
                j++;
            }
            Scanner scan = new Scanner(System.in);
            j = scan.nextInt();
            j=j-1;
            arrlistPNC.remove(j);
            // deletes original CSV file if it exists
            File fileToDelete = new File("vehicles_of_interest.csv");
            if (fileToDelete.exists()){
                fileToDelete.delete();
            }
            FileWriter writer = new FileWriter("vehicles_of_interest.csv");
            // Iterate over the arraylist and write each inner list to the CSV file
            for (List<String> innerList : arrlistPNC) {
            for (String element : innerList) {
                writer.write(element + ",");
            }
            writer.write("\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("A file handling error has occurred. Please see the error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error has occurred:");
            e.printStackTrace();
        }
    }
}
