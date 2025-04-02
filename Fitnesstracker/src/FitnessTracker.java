import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.*;

public class FitnessTracker {
	
	private List<User> users;
	
	public FitnessTracker() {
		this.users = new ArrayList<>();
	}

	public void addUser(User user) {
		users.add(user);
	}

	public List<User> getTopPerformers() {
		return users.stream()
				.sorted(Comparator.comparingDouble(User::getTotalCaloriesBurned).reversed())
				.collect(Collectors.toList());
	}

	public void printLeaderboard() {
		System.out.println("Leaderboard:");
		List<User> ranked = getTopPerformers();
		for (int i = 0; i < ranked.size(); i++) {
			User u = ranked.get(i);
			System.out.printf("%d. %s - %.1f kcal burned\n", i + 1, u.getName(), u.getTotalCaloriesBurned());
		}
	}

	public void generateUserReports() {
		ExecutorService executor = Executors.newFixedThreadPool(4);

		List<Callable<String>> tasks = users.stream()
				.map(user -> (Callable<String>) () -> {
					return String.format(
							"User: %s\nTotal Workouts: %d\nTotal Calories Burned: %.1f\n",
							user.getName(),
							user.getWorkoutLog().size(),
							user.getTotalCaloriesBurned()
					);
				})
				.toList();

		try {
			List<Future<String>> results = executor.invokeAll(tasks);

			System.out.println("\n User Reports Generated Concurrently:");
			for (Future<String> result : results) {
				System.out.println("-------------");
				System.out.println(result.get());
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}
	}


}
