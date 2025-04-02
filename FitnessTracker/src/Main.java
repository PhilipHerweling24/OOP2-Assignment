import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FitnessTracker tracker = new FitnessTracker();

        User philip = new User("Philip", 77.0, 1.90, new WeightLossGoal(80, 75));
        User bob = new User("Bob", 80.0, 1.75, new StrengthGoal(10, 100));
        User chloe = new User("Chloe", 55.0, 1.60, new WeightLossGoal(60, 50));

        tracker.addUser(philip);
        tracker.addUser(bob);
        tracker.addUser(chloe);

        System.out.println("Days since Philip's last workout: " + philip.daysSinceLastWorkout());


        boolean running = true;

        while (running) {
            System.out.println("\n=== Fitness Tracker Menu ===");
            System.out.println("1. Log workout for Philip");
            System.out.println("2. Show Philip's progress");
            System.out.println("3. Show Philip's progress (German)");
            System.out.println("4. Show leaderboard");
            System.out.println("5. Generate user reports (concurrent)");
            System.out.println("6. Group Philip's workouts by type");
            System.out.println("7. Save workouts to file");
            System.out.println("8. Load workouts from file");
            System.out.println("9. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Logging workout for Philip...");
                    philip.logWorkout(new Workout("Cardio", Duration.ofMinutes(30), 250, LocalDateTime.now()));
                    System.out.println("Workout logged.");
                }
                case 2 -> System.out.println("Progress: " + philip.getProgress());
                case 3 -> System.out.println("German: " + philip.getLocalizedProgress(Locale.GERMAN));
                case 4 -> tracker.printLeaderboard();
                case 5 -> tracker.generateUserReports();
                case 6 -> {
                    System.out.println("Workouts grouped by type:");
                    philip.getWorkoutCountByType().forEach((type, count) ->
                            System.out.println(type + ": " + count + " workouts"));
                }
                case 7 -> {
                    philip.saveWorkoutsToFile();
                    bob.saveWorkoutsToFile();
                    chloe.saveWorkoutsToFile();
                    System.out.println("Workouts saved.");
                }
                case 8 -> {
                    philip.loadWorkoutsFromFile();
                    bob.loadWorkoutsFromFile();
                    chloe.loadWorkoutsFromFile();
                    System.out.println("Workouts loaded.");
                }
                case 9 -> {
                    running = false;
                    System.out.println("Exiting app. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
