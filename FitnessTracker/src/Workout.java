import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Workout(String type, Duration duration, double calsBurned, LocalDateTime workoutDate) {
	
	//Formatter for workoutDate DD/MM/YYYY HH:mm
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	//Method which returns the formatted date
	public String formattedWorkoutDate() {
		return workoutDate.format(FORMATTER);
	}

	//Method to determine if a workout was long or not
	public boolean isLongWorkout(){
		return duration.toMinutes() > 60;
	}
	

}
