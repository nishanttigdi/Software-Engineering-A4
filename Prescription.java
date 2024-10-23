import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private ArrayList<String> postRemarks = new ArrayList<>(); //initialization of remarks list
    private static final int MAX_REMARKS = 2; // Maximum of 2 remarks allowed

    // Setters for the Prescription fields
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSphere(float sphere) {
        this.sphere = sphere;
    }

    public void setAxis(float axis) {
        this.axis = axis;
    }

    public void setCylinder(float cylinder) {
        this.cylinder = cylinder;
    }

    public void setExaminationDate(Date examinationDate) {
        this.examinationDate = examinationDate;
    }

    public void setOptometrist(String optometrist) {
        this.optometrist = optometrist;
    }

    // add a prescription with validation
    public boolean addPrescription() {
        // Validation logic...
        if (firstName.length() < 4 || firstName.length() > 15) {
            System.out.println("Validation failed: First name must be between 4 and 15 characters.");
            return false;
        }
        if (lastName.length() < 4 || lastName.length() > 15) {
            System.out.println("Validation failed: Last name must be between 4 and 15 characters.");
            return false;
        }
        if (address.length() < 20) {
            System.out.println("Validation failed: Address must be at least 20 characters.");
            return false;
        }
        if (sphere < -20.00 || sphere > 20.00) {
            System.out.println("Validation failed: Sphere must be between -20.00 and 20.00.");
            return false;
        }
        if (cylinder < -4.00 || cylinder > 4.00) {
            System.out.println("Validation failed: Cylinder must be between -4.00 and 4.00.");
            return false;
        }
        if (axis < 0 || axis > 180) {
            System.out.println("Validation failed: Axis must be between 0 and 180.");
            return false;
        }
        if (examinationDate == null) {
            System.out.println("Validation failed: Examination date cannot be null.");
            return false;
        }
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Validation failed: Optometrist name must be between 8 and 25 characters.");
            return false;
        }

        // Save prescription to file
        savePrescriptionToFile();
        return true;
    }

    // Method to save prescription to file
    private void savePrescriptionToFile() {
        try (FileWriter writer = new FileWriter("presc.txt", true)) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Name: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + examinationDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("-------------------------\n");
            writer.flush();
            System.out.println("Prescription saved to presc.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add a remark with validation
    public boolean addRemark(String remark, String category) {
        // Debug: Print the current number of remarks
        System.out.println("Current number of remarks: " + postRemarks.size());

        // Validate the number of remarks (maximum of 2)
        if (postRemarks.size() >= MAX_REMARKS) {
            System.out.println("Validation failed: A prescription cannot have more than 2 remarks.");
            return false;
        }

        // Validate the number of words and the first word's capitalization
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("Validation failed: Remark must be between 6 and 20 words, and the first word must start with an uppercase letter.");
            return false;
        }

        // Validate the category
        if (!category.equalsIgnoreCase("Client") && !category.equalsIgnoreCase("Optometrist")) {
            System.out.println("Validation failed: Category must be 'Client' or 'Optometrist'.");
            return false;
        }

        // If validation passes, add the remark to the file and to the list
        postRemarks.add(remark);  // Add the remark to the list
        System.out.println("Remark added successfully. Total remarks now: " + postRemarks.size());  

        saveRemarkToFile(remark, category);
        return true;
    }

    // Method to save remark to file
    private void saveRemarkToFile(String remark, String category) {
        try (FileWriter writer = new FileWriter("remark.txt", true)) {
            writer.write("Category: " + category + "\n");
            writer.write("Remark: " + remark + "\n");
            writer.write("-------------------------\n");
            writer.flush();
            System.out.println("Remark saved to remark.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
