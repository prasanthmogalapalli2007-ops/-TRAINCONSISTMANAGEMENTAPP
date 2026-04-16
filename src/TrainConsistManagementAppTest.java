import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

public class TrainConsistManagementAppTest {

    // Passenger Bogie
    static class Bogie {
        String name;
        int capacity;

        Bogie(String name, int capacity) {
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

        // ---------------- Passenger Bogies ----------------
        List<Bogie> bogieList = new ArrayList<>();

        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));
        bogieList.add(new Bogie("Sleeper", 70));
        bogieList.add(new Bogie("AC Chair", 55));

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

        // -------- UC13: PERFORMANCE COMPARISON --------

        // Create large dataset
        List<Bogie> bigList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            bigList.add(new Bogie("Sleeper", i % 100));
        }

        // LOOP METHOD
        long startLoop = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : bigList) {
            if (b.capacity > 60) {
                loopResult.add(b);
            }
        }

        long endLoop = System.nanoTime();
        long loopTime = endLoop - startLoop;

        // STREAM METHOD
        long startStream = System.nanoTime();

        List<Bogie> streamResult = bigList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        long endStream = System.nanoTime();
        long streamTime = endStream - startStream;

        // OUTPUT
        System.out.println("\n--- Performance Comparison ---");
        System.out.println("Loop Time (ns): " + loopTime);
        System.out.println("Stream Time (ns): " + streamTime);

        sc.close();
    }
}