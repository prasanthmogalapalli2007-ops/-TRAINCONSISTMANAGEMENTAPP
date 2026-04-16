import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

public class TrainConsistManagementAppTest {

    // Passenger Bogie class
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

    // Goods Bogie class (for UC12)
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

        System.out.println("Sorted Bogies:");
        for (Bogie b : bogieList) {
            b.display();
        }

        // -------- UC8: FILTER --------
        List<Bogie> filteredList = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        System.out.println("\nFiltered Bogies (capacity > 60):");
        for (Bogie b : filteredList) {
            b.display();
        }

        // -------- UC9: GROUPING --------
        Map<String, List<Bogie>> groupedBogies = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        System.out.println("\nGrouped Bogies:");
        for (String type : groupedBogies.keySet()) {
            System.out.println(type + ":");
            for (Bogie b : groupedBogies.get(type)) {
                b.display();
            }
        }

        // -------- UC10: REDUCE --------
        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        System.out.println("\nTotal Seating Capacity: " + totalCapacity);

        // -------- UC11: REGEX --------
        System.out.println("\nEnter Train ID (TRN-1234): ");
        String trainId = sc.nextLine();

        System.out.println("Enter Cargo Code (PET-AB): ");
        String cargoCode = sc.nextLine();

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        if (trainPattern.matcher(trainId).matches()) {
            System.out.println("Valid Train ID");
        } else {
            System.out.println("Invalid Train ID");
        }

        if (cargoPattern.matcher(cargoCode).matches()) {
            System.out.println("Valid Cargo Code");
        } else {
            System.out.println("Invalid Cargo Code");
        }

        // -------- UC12: SAFETY CHECK --------
        List<GoodsBogie> goodsList = new ArrayList<>();

        // Example data
        goodsList.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsList.add(new GoodsBogie("Open", "Coal"));
        goodsList.add(new GoodsBogie("Box", "Grain"));

        // Safety Rule: Cylindrical → only Petroleum
        boolean isSafe = goodsList.stream()
                .allMatch(g ->
                        !g.type.equalsIgnoreCase("Cylindrical") ||
                                g.cargo.equalsIgnoreCase("Petroleum")
                );

        if (isSafe) {
            System.out.println("\nTrain is SAFETY COMPLIANT");
        } else {
            System.out.println("\nTrain is NOT SAFE");
        }

        sc.close();
    }
}