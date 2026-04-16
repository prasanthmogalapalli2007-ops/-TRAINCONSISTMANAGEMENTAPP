import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

// Custom Exception (UC14)
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

public class TrainConsistManagementAppTest {

    // Passenger Bogie
    static class Bogie {
        String name;
        int capacity;

        // Constructor with validation
        Bogie(String name, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.name = name;
            this.capacity = capacity;
        }

        void display() {
            System.out.println(name + " - Capacity: " + capacity);
        }
    }

    // Goods Bogie
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Bogie> bogieList = new ArrayList<>();

        try {
            // Valid bogies
            bogieList.add(new Bogie("Sleeper", 72));
            bogieList.add(new Bogie("AC Chair", 56));
            bogieList.add(new Bogie("First Class", 24));

            // Invalid bogie (for testing)
            bogieList.add(new Bogie("Invalid", -10));

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // -------- UC7: SORT --------
        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        // -------- UC8: FILTER --------
        List<Bogie> filteredList = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        // -------- UC9: GROUPING --------
        Map<String, List<Bogie>> groupedBogies = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // -------- UC10: REDUCE --------
        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        // -------- UC11: REGEX --------
        System.out.println("Enter Train ID (TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.println("Enter Cargo Code (PET-AB): ");
        String cargoCode = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println(trainPattern.matcher(trainId).matches() ? "Valid Train ID" : "Invalid Train ID");
        System.out.println(cargoPattern.matcher(cargoCode).matches() ? "Valid Cargo Code" : "Invalid Cargo Code");

        // -------- UC12: SAFETY CHECK --------
        List<GoodsBogie> goodsList = new ArrayList<>();
        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Open", "Coal"));

        boolean isSafe = goodsList.stream()
                .allMatch(g ->
                        !g.type.equalsIgnoreCase("Cylindrical") ||
                                g.cargo.equalsIgnoreCase("Petroleum")
                );

        System.out.println(isSafe ? "Train is SAFE" : "Train is NOT SAFE");

        // -------- UC13: PERFORMANCE --------
        List<Bogie> bigList = new ArrayList<>();

        try {
            for (int i = 1; i <= 100000; i++) {
                bigList.add(new Bogie("Sleeper", i % 100 + 1)); // always valid
            }
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }

        // Loop timing
        long startLoop = System.nanoTime();
        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bigList) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }
        long endLoop = System.nanoTime();

        // Stream timing
        long startStream = System.nanoTime();
        List<Bogie> streamResult = bigList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long endStream = System.nanoTime();

        System.out.println("\nLoop Time: " + (endLoop - startLoop));
        System.out.println("Stream Time: " + (endStream - startStream));

        // -------- UC14: RESULT --------
        System.out.println("\nValid Bogies in System:");
        for (Bogie b : bogieList) {
            b.display();
        }

        sc.close();
    }
}