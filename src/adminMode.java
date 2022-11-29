import java.io.*;
import java.util.*;

public class adminMode {

    void viewPNCfile() throws FileNotFoundException {
        try {
            File PNCFile = new File("vehicles_of_interest.csv");
            ArrayList<ArrayList<String>> PNC = new ArrayList<ArrayList<String>>();
            Scanner scannedPNCFile = new Scanner(PNCFile);
            while(scannedPNCFile.hasNextLine()){
                String line = scannedPNCFile.nextLine();
                String[] values = line.split(",");
                PNC.add(new ArrayList<String>(Arrays.asList(values)));
            };
            recordMode recordMode = new recordMode();
            recordMode.viewCars(PNC);
            scannedPNCFile.close();
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
        recordMode recordMode = new recordMode();
        recordMode.displayStartMenu();
    }
    void amendVehicleInPNCfile() {

    }
}
