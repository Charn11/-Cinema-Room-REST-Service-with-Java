import java.util.*;
import java.time.Instant;
import java.time.temporal.ChronoField;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Instant> instantList = createInstantList(scanner);

        long result = getMaxMinusMin(instantList); 

        System.out.println(result);
    }  

    public static List<Instant> createInstantList(Scanner scanner) {
        List<Instant> instantList = new ArrayList<>();
        instantList.add(Instant.parse(scanner.nextLine()));
        instantList.add(Instant.parse(scanner.nextLine()));
        instantList.add(Instant.parse(scanner.nextLine()));
        instantList.add(Instant.parse(scanner.nextLine()));

        return instantList;
    }

    private static long getMaxMinusMin(List<Instant> instantList) {
        Optional<Instant> max = instantList.stream().max(Comparator.naturalOrder());
        Optional<Instant> min= instantList.stream().min(Comparator.naturalOrder());
        long maxSec = max.get().getEpochSecond();
        long minSec = min.get().getEpochSecond();
        return maxSec-minSec;
    }
}