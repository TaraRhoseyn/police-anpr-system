public class App {

    // policeofficer class
    public class PoliceOfficer {
        private int staffNum;
    }
    // adminofficer class
    public class AdminOfficer {
        
    }
    // vehicle Class
    public class Vehicle {
        /*
        Class to store all vehicle information
        including 
        */
        // class attributes
        public String VRN;
        public String make;
        public String model;
        public int yearOfManufacture;
        public String colour;
        public String information;
        // methods
        public void setVRN(String VRNinput) {
            try {
                /* Safety feature to ensure no spaces are in VRN that would compromise 
                the comparison between input and PNC data file */
                VRN = VRNinput.replaceAll(" ", "");
                if (VRN.length() > 10) {
                    System.out.println("VRN is too long");
                }
            } catch (Exception  e) {
                System.out.println(e);
            }
        }
    }
    // camera class
    public class Camera {
        private int operatorNum;
        // ArrayList: carsInMemory
    }
    


    public static void main(String[] args) throws Exception {
        
    }
}