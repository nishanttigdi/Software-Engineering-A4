import org.junit.jupiter.api.Test;
import java.util.Date;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

   
    private Date createDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    // addPrescription()

    @Test
    void testAddPrescriptionValid() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        assertTrue(prescription.addPrescription(), "Prescription should be added successfully.");
    }

    @Test
    void testAddPrescriptionInvalidFirstName() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("Jo");  // less than 4 characters
        prescription.setLastName("DoeSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        assertFalse(prescription.addPrescription(), "Prescription should fail due to invalid first name.");
    }

    @Test
    void testAddPrescriptionInvalidLastName() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("D");  // less than 4 characters
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        assertFalse(prescription.addPrescription(), "Prescription should fail due to invalid last name.");
    }

    @Test
    void testAddPrescriptionInvalidAddress() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSS");
        prescription.setAddress("Short Addr");  // less than 20 characters
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        assertFalse(prescription.addPrescription(), "Prescription should fail due to invalid address.");
    }

    @Test
    void testAddPrescriptionInvalidSphere() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-25.00f);  // out of range
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        assertFalse(prescription.addPrescription(), "Prescription should fail due to invalid sphere value.");
    }

    @Test
    void testAddPrescriptionInvalidOptometrist() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. O");  // Invalid: less than 8 characters

        assertFalse(prescription.addPrescription(), "Prescription should fail due to invalid optometrist name.");
    }

    // Test cases for addRemark()

    @Test
    void testAddRemarkValid() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        // Add valid remark
        assertTrue(prescription.addRemark("Great service and thorough eye test.", "Client"), "Remark should be added successfully.");
    }

    @Test
    void testAddRemarkInvalidRemarkTooShort() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        // Invalid: Less than 6 words
        assertFalse(prescription.addRemark("Short remark.", "Client"), "Remark should fail due to insufficient words.");
    }

    @Test
    void testAddRemarkInvalidRemarkTooLong() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeSS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        // Invalid: More than 20 words
        String longRemark = "This is a very long remark that should fail because it has more than twenty words which is too many for a remark to be valid.";
        assertFalse(prescription.addRemark(longRemark, "Client"), "Remark should fail due to exceeding the word limit.");
    }

    @Test
    void testAddRemarkInvalidCategory() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        // Invalid category
        assertFalse(prescription.addRemark("Great service.", "Other"), "Remark should fail due to invalid category.");
    }


    @Test
    void testAddRemarkInvalidFirstWordCapitalization() {
        Prescription prescription = new Prescription();
        prescription.setFirstName("John");
        prescription.setLastName("DoeS");
        prescription.setAddress("1234 Elm Street, Suburbia, 12345, Country");
        prescription.setSphere(-2.50f);
        prescription.setCylinder(-1.25f);
        prescription.setAxis(90);
        prescription.setExaminationDate(createDate("12/10/23"));
        prescription.setOptometrist("Dr. Opthalmist");

        // Invalid: First word not capitalized
        assertFalse(prescription.addRemark("great service and thorough eye test.", "Client"), "Remark should fail due to first word not capitalized.");
    }
}
