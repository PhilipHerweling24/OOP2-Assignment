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
	

}
