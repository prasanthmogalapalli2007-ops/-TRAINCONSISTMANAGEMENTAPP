import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

// -------- UC14 Custom Exception --------
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

// -------- UC15 Runtime Exception --------
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String message) {
        super(message);
    }
}

public class TrainConsistManagementAppTest {

    // Passenger Bogie
    static class Bogie {
        String name;
        int capacity;

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

        GoodsBogie(String type) {
            this.type = type;
        }

        // UC15: Safe Cargo Assignment
        void assignCargo(String cargo) {
            try {
                // Rule: Rectangular cannot carry Petroleum
                if (type.equalsIgnoreCase("Rectangular") && cargo.equalsIgnoreCase("Petroleum")) {
                    throw new CargoSafetyException("Unsafe Cargo Assignment!");
                }

                this.cargo = cargo;
                System.out.println("Cargo assigned successfully: " + cargo);

            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("Cargo assignment process completed.\n");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Bogie> bogieList = new ArrayList<>();

        try {
            bogieList.add(new Bogie("Sleeper", 72));
            bogieList.add(new Bogie("AC Chair", 56));
            bogieList.add(new Bogie("First Class", 24));

            // Invalid test
            bogieList.add(new Bogie("Invalid", 0));

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // -------- UC7: SORT --------
        bogieList.sort(Comparator.comparingInt(b -> b.capacity));

        // -------- UC8: FILTER --------
        List<Bogie> filtered = bogieList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());

        // -------- UC9: GROUP --------
        Map<String, List<Bogie>> grouped = bogieList.stream()
                .collect(Collectors.groupingBy(b -> b.name));

        // -------- UC10: REDUCE --------
        int total = bogieList.stream()
                .map(b -> b.capacity)
                .reduce(0, Integer::sum);

        // -------- UC11: REGEX --------
        System.out.println("Enter Train ID:");
        String trainId = sc.nextLine();

        Pattern p = Pattern.compile("TRN-\\d{4}");
        System.out.println(p.matcher(trainId).matches() ? "Valid" : "Invalid");

        // -------- UC12: SAFETY --------
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical"));
        goods.add(new GoodsBogie("Open"));

        boolean safe = goods.stream()
                .allMatch(g -> !g.type.equals("Cylindrical") || true);

        System.out.println("Safety Check: " + (safe ? "Safe" : "Not Safe"));

        // -------- UC13: PERFORMANCE --------
        List<Bogie> bigList = new ArrayList<>();
        try {
            for (int i = 1; i <= 100000; i++) {
                bigList.add(new Bogie("Sleeper", i % 100 + 1));
            }
        } catch (Exception e) {}

        long start1 = System.nanoTime();
        List<Bogie> loopList = new ArrayList<>();
        for (Bogie b : bigList) {
            if (b.capacity > 60) loopList.add(b);
        }
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<Bogie> streamList = bigList.stream()
                .filter(b -> b.capacity > 60)
                .collect(Collectors.toList());
        long end2 = System.nanoTime();

        System.out.println("Loop Time: " + (end1 - start1));
        System.out.println("Stream Time: " + (end2 - start2));

        // -------- UC15: TRY-CATCH-FINALLY --------
        System.out.println("\n--- UC15: Cargo Assignment ---");

        GoodsBogie g1 = new GoodsBogie("Cylindrical");
        GoodsBogie g2 = new GoodsBogie("Rectangular");

        // Safe
        g1.assignCargo("Petroleum");

        // Unsafe
        g2.assignCargo("Petroleum");

        // Program continues
        System.out.println("Program continues safely after exception.");

        sc.close();
    }
}