import java.util.*;
import java.util.stream.Collectors;

public class TrainConsistManagementAppTest {

    // Bogie class inside
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

        // -------- UC10: REDUCE (TOTAL CAPACITY) --------
        int totalCapacity = bogieList.stream()
                .map(b -> b.capacity)          // extract capacity
                .reduce(0, Integer::sum);      // sum all

        System.out.println("\nTotal Seating Capacity: " + totalCapacity);
    }
}