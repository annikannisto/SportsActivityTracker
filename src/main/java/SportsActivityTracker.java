import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SportsActivityLogger {
    private final List<Activity> activities;

    public SportsActivityLogger() {
        this.activities = new ArrayList<>();
    }

    private static class Activity {
        String description;
        int duration;

        public Activity(String description, int duration) {
            this.description = description;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return description + " - " + duration + " minutes";
        }
    }

    public void logActivity(String description, int duration) {
        activities.add(new Activity(description, duration));
    }

    public String getLastActivity() {
        if (!activities.isEmpty()) {
            return activities.get(activities.size() - 1).toString();
        }
        return "";
    }

    public int getNumberOfActivities() {
        return activities.size();
    }

    public void viewActivities() {
        if (activities.isEmpty()) {
            System.out.println("No activities logged yet.");
            return;
        }
        System.out.println("Logged Activities:");
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

    public void calculateTotalTime() {
        int total = activities.stream().mapToInt(a -> a.duration).sum();
        System.out.println("Total time spent on sports this week: " + total + " minutes");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SportsActivityLogger logger = new SportsActivityLogger();
        boolean running = true;

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Log an activity");
            System.out.println("2. View logged activities");
            System.out.println("3. Calculate total time spent on activities");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter activity description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter activity duration (in minutes): ");
                    int duration = scanner.nextInt();
                    logger.logActivity(description, duration);
                    break;
                case 2:
                    logger.viewActivities();
                    break;
                case 3:
                    logger.calculateTotalTime();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
        scanner.close();
    }
}