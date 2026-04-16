import java.util.Arrays;

public class TrainConsistManagementAppTest {

    // Binary Search with Exception Handling
    public static boolean searchBogie(String[] bogieIds, String key) {

        // 🔥 UC20: Fail-Fast Validation
        if (bogieIds == null || bogieIds.length == 0) {
            throw new IllegalStateException("No bogies available for search");
        }

        // Sort before Binary Search
        Arrays.sort(bogieIds);

        int low = 0;
        int high = bogieIds.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int compare = key.compareTo(bogieIds[mid]);

            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        String[] bogieIds = {"BG101", "BG205", "BG309"};

        try {
            boolean result = searchBogie(bogieIds, "BG205");

            if (result) {
                System.out.println("Bogie Found");
            } else {
                System.out.println("Bogie Not Found");
            }

        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}