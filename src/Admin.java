import java.io.*;
import java.util.*;

public class Admin {
    
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
            Record record = new Record();
            record.viewCars(arrlist);
            record.displayStartMenu();
        } catch(Exception e){
            System.out.println("\nA file error has occurred. Please see error message:\n");
            System.out.println(e);
        }
    }
    public void removePNCvehicle(){
        try {
            /*
            BUG SOMEWHERE HERE TODO 
            CAR CORRECTLY BEING REMOVED
            BUT NEW FILE IS ALL ONE STRING
            RATHER THAN BROKEN UP...
            */
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
            // write back to PNC file...
            File fileToDelete = new File("vehicles_of_interest.csv");
            if (fileToDelete.exists()){
                fileToDelete.delete();
            }
            FileWriter newFile = new FileWriter("vehicles_of_interest.csv");
            for(int x=0; x<arrlistPNC.size(); x++) {  // iterates through row
                for(int y=0; y<arrlistPNC.get(x).size(); y++) {   // iterates through col
                    String colValue = arrlistPNC.get(x).get(y);
                    String row = String.format("%s,", colValue);
                    // FIX: final comma appearing after third piece of data in row
                    newFile.append(row);
                }
            }
            newFile.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
