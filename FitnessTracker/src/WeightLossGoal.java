// WeightLossGoal Class
public final class WeightLossGoal implements FitnessGoal {

	private final double startWeight;
	private final double targetWeight;
	
	//Constructor for weightLossGoal class
	public WeightLossGoal(double startWeight, double targetWeight) {
		this.startWeight = startWeight;
		this.targetWeight = targetWeight;
	}
	
	//Getters

	public double getStartWeight() {
		return startWeight;
	}
	public double getTargetWeight() {
		return targetWeight;
	}
	
	//Method to get goal description
	@Override
	public String getGoalDescription() {
		return "Goal: Get from " + startWeight + "kg to " + targetWeight + "kg.";
	}
	
}//end of class