import java.util.Arrays;

public class TrainConsistManagementAppTest {

    // Binary Search Method
    public static boolean binarySearchBogie(String[] bogieIds, String key) {

        // Step 1: Sort the array (important)
        Arrays.sort(bogieIds);

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int compare = key.compareTo(bogieIds[mid]);

            if (compare == 0) {
                return true; // Found
            } else if (compare < 0) {
                high = mid - 1; // Search left
            } else {
                low = mid + 1; // Search right
            }
        }

        return false; // Not Found
    }

    public static void main(String[] args) {

        String[] bogieIds = {"BG101", "BG205", "BG309", "BG412", "BG550"};

        String searchKey = "BG309";

        boolean result = binarySearchBogie(bogieIds, searchKey);

        if (result) {
            System.out.println("Bogie Found: " + searchKey);
        } else {
            System.out.println("Bogie Not Found: " + searchKey);
        }
    }
}