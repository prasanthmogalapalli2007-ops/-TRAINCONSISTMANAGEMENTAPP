import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.*;

// -------- UC14 Exception --------
class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String msg) {
        super(msg);
    }
}

// -------- UC15 Runtime Exception --------
class CargoSafetyException extends RuntimeException {
    public CargoSafetyException(String msg) {
        super(msg);
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
    }

    // Goods Bogie
    static class GoodsBogie {
        String type;
        String cargo;

        GoodsBogie(String type) {
            this.type = type;
        }

        void assignCargo(String cargo) {
            try {
                if (type.equalsIgnoreCase("Rectangular") && cargo.equalsIgnoreCase("Petroleum")) {
                    throw new CargoSafetyException("Unsafe Cargo Assignment!");
                }
                this.cargo = cargo;
                System.out.println("Cargo Assigned: " + cargo);
            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Process Completed\n");
            }
        }
    }

    public static void main(String[] args) {

        // -------- UC16: BUBBLE SORT --------
        int[] capacities = {72, 56, 24, 70, 60};

        for (int i = 0; i < capacities.length - 1; i++) {
            for (int j = 0; j < capacities.length - i - 1; j++) {
                if (capacities[j] > capacities[j + 1]) {
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }

        System.out.println("Bubble Sorted Capacities:");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        // -------- UC17: ARRAYS.SORT --------
        String[] bogieNames = {"Sleeper", "AC Chair", "First Class", "General", "Luxury"};

        System.out.println("\n\nBefore Sorting:");
        System.out.println(Arrays.toString(bogieNames));

        Arrays.sort(bogieNames); // Built-in sorting

        System.out.println("After Sorting:");
        System.out.println(Arrays.toString(bogieNames));

        // -------- UC15 DEMO --------
        System.out.println("\nUC15 Demo:");
        GoodsBogie g1 = new GoodsBogie("Cylindrical");
        GoodsBogie g2 = new GoodsBogie("Rectangular");

        g1.assignCargo("Petroleum"); // safe
        g2.assignCargo("Petroleum"); // unsafe
    }
}