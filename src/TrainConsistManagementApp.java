import java.util.HashSet;
import java.util.Set;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("UC3 Track Unique Bogie IDs\n");

        // Create HashSet for Bogie IDs
        Set<String> bogieIDs = new HashSet<>();

        // Adding Bogie IDs (including duplicates)
        bogieIDs.add("BG101");
        bogieIDs.add("BG102");
        bogieIDs.add("BG103");
        bogieIDs.add("BG104");
        bogieIDs.add("BG101"); // Duplicate
        bogieIDs.add("BG102"); // Duplicate

        System.out.println("Bogie IDs After Insertion: " + bogieIDs);

        System.out.println("\nNote:");
        System.out.println("Duplicates are automatically ignored by HashSet.");

        System.out.println("\nUC3 uniqueness validation completed...");
    }
}