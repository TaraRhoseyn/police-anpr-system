import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Vehicle {
    public String VRN;
    public String date;
    public String time;
    public String make;
    public String model;
    public int yearOfManufacture;
    public String colour;
    public String information;
    // constructor class:
    public Vehicle(String VRN, String date, String time) {
        /*
        Constructor class for vehicle information
        as read by camera
        */
        this.VRN = setVRN(VRN);
        this.date = setDate();
        this.time = time;
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
        String message = "";
        str.replaceAll(" ", "");
        str.toUpperCase();
        message = (str.isEmpty()) ? "Please submit a Vehicle Registration Number." : "";
        if ((str.length() >= 12) || (str.length() <= 3)) {
            message = "The submitted Vehicle Registration Number must be between 3 and 12 characters long.";
        } else {
            this.VRN = str;
            message = "Vehicle Registration Number successfully logged.";
        }
        return message;
    }
    public String setDate() {
        // TODO: check format is same as in assignment
        /*
         * https://howtodoinjava.com/java/date-time/localdate-format-example/
         */
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter
            .ofLocalizedDate(FormatStyle.SHORT));
        return formattedDate;
    }
    public void setTime(String str) {
        time = str;
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

