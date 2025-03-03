
// StrengthGoal class
public final class StrengthGoal implements FitnessGoal {
	
	private final int targetReps;
	private final double targetWeightLifted;
	
	//Constructor for StrngthGoal
	public StrengthGoal(int targetReps, double targetWeightLifted) {
		this.targetReps = targetReps;
		this.targetWeightLifted = targetWeightLifted;
	}
	
	@Override
	public String getGoalDescription() {
		return "Goal: Lift " + targetWeightLifted + "kg for " + targetReps + " reps.";
	}
	
}//end of class
