import java.util.ArrayList;
import java.util.List;

public class TrainConsistManagementApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Initialize empty train consist list
        List<String> trainConsists = new ArrayList<>();

        System.out.println("Train Initialized Successfully...");
        System.out.println("Initial Bogie Count: " + trainConsists.size());
        System.out.println("Current Train Consists: " + trainConsists);
        System.out.println("System ready for operations...");
    }
}