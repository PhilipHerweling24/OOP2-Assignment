import java.text.MessageFormat;
import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


public class User {
	
	private String name;
	private double weight;
	private double height;
	private List<Workout> workoutLog;
	private FitnessGoal goal;

	//fields needed for NIO2
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private final Path FILE_PATH;


	//Constructor for user class
	public User (String name, double weight, double height, FitnessGoal goal) {
		this.name = name;
		this.weight = weight;
		this.height = height;
		this.workoutLog = new ArrayList<>();
		this.goal = goal;

		this.FILE_PATH = Paths.get(name.toLowerCase() + "_workouts.txt");
		loadWorkoutsFromFile();
		
	}
	
	//Method which logs a new Workout
	public void logWorkout (Workout workout) {
		workoutLog.add(workout);
	}
	
	//Method to get all workouts
	public List<Workout> getWorkoutLog(){
		
		//Returns a copy of the workoutLog to preserve immutability
		return new ArrayList<>(workoutLog);
	}

	//method which prints all a users workouts
	public void printWorkoutLogs() {
		if(workoutLog.isEmpty()) {
			System.out.println(name + " has no workouts logged.");
		} else {
			System.out.println("Workout log for " + name + ":");
			for(Workout w : workoutLog){
				System.out.println(w.type() + " || Duration: " + w.duration().toMinutes() +
						" mins || Calories Burned: " + w.calsBurned() + " kcals || Date: " +
						w.formattedWorkoutDate());
			}
		}
	}

	//Method which returns a count for how many times a particular
	//Workout was done
	public Map<String, Long> getWorkoutCountByType() {
		return workoutLog.stream()
				.collect(Collectors.groupingBy(
						Workout::type,
						Collectors.counting()
				));
	}

	//Method which returns total cals burned for each workout type
	public Map<String, Double> getCaloriesByType() {
		return workoutLog.stream()
				.collect(Collectors.groupingBy(
						Workout::type,
						Collectors.summingDouble(Workout::calsBurned)
				));
	}

	//Method which gets a users total calories burned
	public double getTotalCaloriesBurned() {
		return workoutLog.stream()
				.mapToDouble(Workout::calsBurned)
				.sum();
	}

	//Method to work out the days between a users last workout
	public long daysSinceLastWorkout() {
		if (workoutLog.isEmpty()) return -1;

		Workout lastWorkout = workoutLog.get(workoutLog.size() - 1);
		return Duration.between(lastWorkout.workoutDate(), LocalDateTime.now()).toDays();
	}

	//Method to get a users progress towards there goal
	public String getProgress() {
		if(goal == null) {
			return "No goal set.";
		}

		return switch (goal) {
			case WeightLossGoal weightloss -> {
				double lost = weightloss.getStartWeight() -weight;
				double toLose = weightloss.getStartWeight() - weightloss.getTargetWeight();
				double progress = (lost /toLose) * 100;
				yield String.format("You have lost %.1f kg (%.1f%% of your goal)",lost, progress);
			}//end of weight loss case

			case StrengthGoal _ -> "Progress tracking for strength goals not implmented yet.";


		};//end of switch
	}//end of getProgress method


	//Basic BMI calculator (BMI = kg / m^2) where m is height in meters
	public double calculateBMI() {
		return weight / (height * height);
	}

	//Method to save workouts to a file
	public void saveWorkoutsToFile() {
		try {
			String content = workoutLog.stream()
					.map(w -> w.type() + "," + w.duration().toMinutes() + "," + w.calsBurned() +
							"," + w.workoutDate().format(FORMATTER))
					.collect(Collectors.joining("\n"));
			Files.writeString(FILE_PATH, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.out.println("Error saving workout log to file: " + e.getMessage());
		}
	}

	//Method to load workouts from file
	public void loadWorkoutsFromFile() {
		if (!Files.exists(FILE_PATH)) return;

		try {
			List<String> lines = Files.readAllLines(FILE_PATH);
			for (String line : lines) {
				String[] parts = line.split(",");
				if (parts.length == 4) {
					String type = parts[0];
					Duration duration = Duration.ofMinutes(Long.parseLong(parts[1]));
					double calories = Double.parseDouble(parts[2]);
					LocalDateTime date = LocalDateTime.parse(parts[3], FORMATTER);
					workoutLog.add(new Workout(type, duration, calories, date));
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading workouts: " + e.getMessage());
		}
	}

	//Method to show localisation
	public String getLocalizedProgress(Locale locale) {
		if (!(goal instanceof WeightLossGoal weightLoss)) {
			return "Progress reporting not supported for this goal.";
		}

		ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

		double lost = weightLoss.getStartWeight() - weight;
		double toLose = weightLoss.getStartWeight() - weightLoss.getTargetWeight();
		double progress = (lost / toLose) * 100;

		String pattern = bundle.getString("progress_message");
		return MessageFormat.format(pattern,
				String.format("%.1f", lost),
				String.format("%.1f", progress));
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

	public FitnessGoal getGoal() {
		return goal;
	}


}
