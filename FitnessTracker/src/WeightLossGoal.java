
// WeightLossGoal Class 
public final class WeightLossGoal implements FitnessGoal {
	
	private final double targetWeight;
	
	//Constructor for weightLossGoal class
	public WeightLossGoal(double targetWeight) {
		this.targetWeight = targetWeight;
	}
	
	//Method to get goal description
	@Override
	public String getGoalDescription() {
		return "Goal: Reach " + targetWeight + "kg";
	}
	
}//end of class