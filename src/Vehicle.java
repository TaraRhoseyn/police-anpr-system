import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.LocalTime;

public class Vehicle {
    public String VRN;
    public String date;
    public String time;
    public String make;
    public String model;
    public int yearOfManufacture;
    public String colour;
    public String information;
    public Vehicle(String VRN) {
        /*
        Constructor class for vehicle information
        as read by camera
        */
        this.VRN = setVRN(VRN);
        this.date = setDate();
        this.time = setTime();
    }
    public void Vehicle(String VRN, String make, String model, int yearOfManufacture, String colour, String information) {
        /*
        Constructor class for vehicle information
        in PNC file
        */
        this.VRN = setVRN(VRN);
        this.make = make;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.colour = colour;
        this.information = information;
    }
    public String setVRN(String str) {
        /* Removes any whitespaces, converts to uppercase, and only sets the variable
        to the class attribute if it's not empty and between 3-12 characters. */
        // TODO: logic is working OK, but need to address error messages to user??
        String message = "";
        str.replaceAll(" ", "");
        str.toUpperCase();
        if (str.isEmpty()) {
            System.out.println("Please submit a Vehicle Registration Number.");
        } else if ((str.length() >= 12) || (str.length() <= 3)) {
            System.out.println("The submitted Vehicle Registration Number must be between 3 and 12 characters long.");
        } else {
            System.out.println("Vehicle Registration Number successfully logged.");
            return str;
        }
        // making me do a return statement even if above test cases fail?? how to get around??
        return str;
    }
    public String setDate() {
        // TODO: check format is same as in assignment
        /*
         * https://howtodoinjava.com/java/date-time/localdate-format-example/
         * https://www.tutorialspoint.com/java/java_date_time.htm
         */
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter
            .ofLocalizedDate(FormatStyle.SHORT));
        return formattedDate;
    }
    public String setTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String time = dateFormat.format(System.currentTimeMillis());
        return time;
    }
    public void setMake(String str) {
        make = str;
    }
    public void setModel(String str) {
        model = str;
    }
    public void setYear(int n) {
        // Sets yearOfManufacture if year is between 1900-2023 
        if ((n >= 1900) && (n <= 2023)) {
            yearOfManufacture = n;
        }
    }
    public void setColour(String str) {
        colour = str;
    }
    public void setInfo(String str) {
        information = str;
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

