import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementAppTest {

    // Bogie class inside (avoids all errors)
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

    public static void main(String[] args) {

        // Create list
        List<Bogie> bogieList = new ArrayList<>();

        // Add bogies
        bogieList.add(new Bogie("Sleeper", 72));
        bogieList.add(new Bogie("AC Chair", 56));
        bogieList.add(new Bogie("First Class", 24));

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
    }
}