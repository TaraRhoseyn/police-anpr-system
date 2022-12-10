import java.io.*;
import java.util.*;

public class Admin {
    
    public ArrayList<ArrayList<String>> readPNCfile() throws FileNotFoundException {
        /**
        * This method adds a new entry to a CSV file with vehicle information.
        * It does this by first prompting the user to enter the details of the
        * new vehicle. It then creates a new inner list with these details and
        * adds it to a multidimensional arraylist. Finally, it writes the
        * updated arraylist to the CSV file.
        */
        ArrayList<ArrayList<String>> PNC = new ArrayList<ArrayList<String>>();
        try {
            File PNCFile = new File("vehicles_of_interest.csv");
            Scanner scannedPNCFile = new Scanner(PNCFile);
            while(scannedPNCFile.hasNextLine()){
                String line = scannedPNCFile.nextLine();
                String[] values = line.split(",");
                PNC.add(new ArrayList<String>(Arrays.asList(values)));
            };
            scannedPNCFile.close();
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
        return PNC;
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
    public void removePNCvehicle(){
        /*
        * This method removes an entry from a CSV file and updates the file with the remaining entries. 
        * It does this by first reading the CSV file into a multidimensional arraylist using the readPNCfile method. 
        * It then prompts the user to choose which entry they want to remove and removes it from the arraylist. 
        * Finally, it writes the updated arraylist back to the CSV file.
        */
        try {
            ArrayList<ArrayList<String>> arrlistPNC = readPNCfile();
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
