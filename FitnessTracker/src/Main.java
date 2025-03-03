import java.time.Duration;
import java.time.LocalDateTime;

public class Main {

	public static void main(String[] args) {

		//Testing user code
		User user = new User("Philip", 90.01, 1.89);
		
		//Log some workouts for user
		user.logWorkout(new Workout("cardio", Duration.ofMinutes(45), 300, LocalDateTime.now()));
		user.logWorkout(new Workout("strength", Duration.ofMinutes(60), 450, LocalDateTime.now()));
		
		//Get User info
		System.out.println("user: "+ user.getName());
		System.out.println("Total cals Burned: "+ user.getTotalCalsBurned());
		
		//Testing workout methods
		Workout lastworkout = user.getLastWorkout();
		System.out.println("Last workout type: "+ lastworkout.type());
		System.out.println("Date: "+ lastworkout.formattedWorkoutDate());
	}

}
