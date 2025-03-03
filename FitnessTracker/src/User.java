import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String name;
	private double weight;
	private double height;
	private List<Workout> workoutLog;
	private LocalDateTime workoutDate;
	
	//Constructor for user class
	public User (String name, double weight, double height) {
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.workoutLog = new ArrayList<>();
	}
	
	//Method which logs a new Work out
	public void logWorkout (Workout workout) {
		workoutLog.add(workout);
	}
	
	//Method to get all work outs
	public List<Workout> getWorkouts(){
		
		//Returns a copy of the workoutLog to preserve immutability
		return new ArrayList<>(workoutLog);
	}
	
	//Method which gets work outs of a specific type using Lambda and streams
	public List<Workout> getWorkoutsByType(String type){
		return workoutLog.stream()
				.filter(workout -> workout.type().equalsIgnoreCase(type))
				.toList();
	}
	
	//Method which calculates calories burned using stream
	public double getTotalCalsBurned() {
		return workoutLog.stream()
				.mapToDouble(Workout -> Workout.calsBurned() )
				.sum();
	}
	
	
	//Method which gets your most recent work out
	public Workout getLastWorkout() {
		return workoutLog.isEmpty() ? null : workoutLog.get(workoutLog.size() -1);
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getHeight() {
		return height;
	}

}
