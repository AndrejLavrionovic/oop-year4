package ie.gmit.sw.db4o;

public class Methadata {

	
	// Fields
	private String jarName;
	private int numberOfClasses;
	private int numberOfStable;
	private int numberOfInstable;
	private int numberOfOthers;
	
	// Constructors
	public Methadata() {}

	public Methadata(String jarName, int numberOfClasses, int numberOfStable, int numberOfInstable,
			int numberOfOthers) {
		this.jarName = jarName;
		this.numberOfClasses = numberOfClasses;
		this.numberOfStable = numberOfStable;
		this.numberOfInstable = numberOfInstable;
		this.numberOfOthers = numberOfOthers;
	}
	
	// getters and setters
	public String getJarName() {
		return jarName;
	}
	public void setJarName(String jarName) {
		this.jarName = jarName;
	}
	public int getNumberOfClasses() {
		return numberOfClasses;
	}
	public void setNumberOfClasses(int numberOfClasses) {
		this.numberOfClasses = numberOfClasses;
	}
	public int getNumberOfStable() {
		return numberOfStable;
	}
	public void setNumberOfStable(int numberOfStable) {
		this.numberOfStable = numberOfStable;
	}
	public int getNumberOfInstable() {
		return numberOfInstable;
	}
	public void setNumberOfInstable(int numberOfInstable) {
		this.numberOfInstable = numberOfInstable;
	}
	public int getNumberOfOthers() {
		return numberOfOthers;
	}
	public void setNumberOfOthers(int numberOfOthers) {
		this.numberOfOthers = numberOfOthers;
	}
}
