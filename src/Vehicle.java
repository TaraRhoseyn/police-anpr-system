import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Vehicle {
    /*
    * The Vehicle class is used to store and manipulate information about vehicles.
    * It has two constructors: one for vehicle information as read by a camera, and
    * another for vehicle information in a PNC file. The class also has methods for
    * setting and getting the VRN, date, time, make, model, year of manufacture,
    * colour, and information attributes of a vehicle.
    */
    public String VRN;
    public String date;
    public String time;
    public String make;
    public String model;
    public int yearOfManufacture;
    public String colour;
    public String information;
    public Vehicle(String VRN) {
        // Constructor to be used by Record class
        this.VRN = setVRN(VRN);
        this.date = setDate();
        this.time = setTime();
    }
    public Vehicle(String VRN, String make, String model, int yearOfManufacture, String colour, String information) {
        // Constructor to be used by Admin class
        this.VRN = setVRN(VRN);
        this.make = setMake(make);
        this.model = setModel(model);
        this.yearOfManufacture = setYear(yearOfManufacture);
        this.colour = setColour(colour);
        this.information = setInfo(information);
    }
    public String setVRN(String str) {
        /*
         * This method is used to set the VRN (vehicle registration number) attribute of a Vehicle object. 
         * It takes a string as an argument and removes any whitespace, converts it to uppercase, 
         * and checks that it is not empty and is between 3-12 characters long. If the VRN meets these criteria, 
         * it sets the VRN attribute of the Vehicle object to the provided string and returns it. Otherwise, 
         * it prints an error message to the terminal and calls the addVehicle method from the Record class 
         * to prompt the user to enter a valid VRN.
         */
        str.replaceAll(" ", "");
        str.toUpperCase();
        Record record = new Record();
        if (str.isEmpty()) {
            System.out.println("Please submit a Vehicle Registration Number.");
            record.addVehicle();
        } else if ((str.length() >= 12) || (str.length() <= 3)) {
            System.out.println("The submitted Vehicle Registration Number must be between 3 and 12 characters long.");
            record.addVehicle();
        } else {
            System.out.println("Vehicle Registration Number successfully logged.");
            return str;
        }
        return str;
    }
    public String setDate() {
        // Sets the date attribute to today's date using the java.time package 
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter
            .ofLocalizedDate(FormatStyle.SHORT));
        return formattedDate;
    }
    public String setTime() {
        // Sets the time attribute to the current system time using the java.time package 
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String time = dateFormat.format(System.currentTimeMillis());
        return time;
    }
    public String setMake(String str) {
        return make = str;
    }
    public String setModel(String str) {
        return model = str;
    }
    public int setYear(int n) {
        // Sets yearOfManufacture if year is between 1900-2023 
        if ((n >= 1900) && (n <= 2023)) {
            yearOfManufacture = n;
        }
        return yearOfManufacture;
    }
    public String setColour(String str) {
        return colour = str;
    }
    public String setInfo(String str) {
        return information = str;
    }
    public String getVRN() {
        return this.VRN;
    }
    public String getDate() {
        return this.date;
    }
    public String getTime() {
        return this.time;
    }
    public String getMake() {
        return this.make;
    }
    public String getModel() {
        return this.model;
    }
    public int getYear() {
        return this.yearOfManufacture;
    }
    public String getColour() {
        return this.colour;
    }
    public String getInfo() {
        return this.information;
    }
}

