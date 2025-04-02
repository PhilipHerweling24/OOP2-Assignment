
// StrengthGoal class
public final class StrengthGoal implements FitnessGoal {
	
	private final int targetReps;
	private final double targetWeightLifted;
	
	//Constructor for StrengthGoal
	public StrengthGoal(int targetReps, double targetWeightLifted) {
		this.targetReps = targetReps;
		this.targetWeightLifted = targetWeightLifted;
	}
	
	//Getters
	public int getTargetReps() {
		return targetReps;
	}
	
	public double getTargetWeightLifted() {
		return targetWeightLifted;
	}
	
	@Override
	public String getGoalDescription() {
		return "Goal: Lift " + targetWeightLifted + "kg for " + targetReps + " reps.";
	}
	
}//end of class
