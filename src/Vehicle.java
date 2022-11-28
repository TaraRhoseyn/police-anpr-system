public class Vehicle {
    public String VRN = "";
    public String date = "";
    public String time = "";
    public String make = "";
    public String model = "";
    public int yearOfManufacture = 0;
    public String colour = "";
    public String information = "";
    public void setVRN(String str) {
        // if ((VRN.length()>2) && (VRN.length()<12)) {
        //     // VRN = str.replaceAll(" ","");
        //     VRN = str;
        // }
            // VRN = str.replaceAll(" ","");
        VRN = str;
    }
    public void setDate(String str) {
        date = str;
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
        if ((n > 1900) && (n <= 2023)) {
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
