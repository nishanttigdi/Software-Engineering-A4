import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Prescription prescription = new Prescription();
        
        // Set prescription details
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            Date examDate = sdf.parse("12/10/23"); // Example date

            //  valid prescription details 
            prescription.setFirstName("John");
            prescription.setLastName("DoeS");
            prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
            prescription.setSphere(-2.50f);
            prescription.setCylinder(-1.25f);
            prescription.setAxis(90);
            prescription.setExaminationDate(examDate);
            prescription.setOptometrist("Dr. Opthalmist");

            //  addPrescription function
            if (prescription.addPrescription()) {
                System.out.println("Prescription added successfully.");
            } else {
                System.out.println("Prescription failed validation.");
            }

            
            if (prescription.addRemark("Great service and thorough eye test.", "Client")) {
                System.out.println("Remark added successfully.");
            } else {
                System.out.println("Remark failed validation.");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
