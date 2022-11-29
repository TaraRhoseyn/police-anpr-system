import java.io.*;
import java.util.*;

public class adminMode {
    
    public ArrayList<ArrayList<String>> readPNCfile() throws FileNotFoundException {
        /* Reads through the csv file of vehicles of interest
        and adds them to a multidimensional ArrayList which is returned */
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
        /* Sends the ArrayList passed to another method, viewCars(),
         which iterates over the ArrayList and prints to console */
        try {
            recordMode recordMode = new recordMode();
            recordMode.viewCars(arrlist);
            recordMode.displayStartMenu();
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
    }
    
}
