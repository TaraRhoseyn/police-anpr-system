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
