package ie.gmit.sw.measure;

/**
 * 
 * Instability is an object that represents result for instability for the given class.
 * Object contains Class, its Efferent coupling value, its Afferent Coulpling value,
 * and its Instability degree.
 * 
 * @author g00196984 - Andtej Lavrinovic
 * 
 *
 */
public class Instability {

	// fields for instability
	private Class cl;
	private int ce;
	private int ca;
	private double i;
	
	
	// Constructors (default) generated using eclipse
	/**
	 * Default constructor.
	 */
	public Instability() {}

	// parameterazed constructor generated using eclipse
	/**
	 * Parameterized construcor used for create Instability results.
	 * 
	 * @param cl Inspected class.
	 * @param ce Efferent coupling for given class.
	 * @param ca Afferent coupling for given class.
	 * @param i Instability for given class.
	 */
	public Instability(Class cl, int ce, int ca, double i) {
		super();
		this.cl = cl;
		this.ce = ce;
		this.ca = ca;
		this.i = i;
	}

	// Getters/Setters generated using eclipse
	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @return Returns inspected class
	 */
	public Class getCl() {
		return cl;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @param cl Inspected class
	 */
	public void setCl(Class cl) {
		this.cl = cl;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @return Returns Efferent coupling integer value
	 */
	public int getCe() {
		return ce;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @param ce Efferent Coupling value
	 */
	public void setCe(int ce) {
		this.ce = ce;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @return Returns Afferent coupling integer value
	 */
	public int getCa() {
		return ca;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @param ca Afferent coupling value
	 */
	public void setCa(int ca) {
		this.ca = ca;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @return Returns Instability value
	 */
	public double getI() {
		return i;
	}

	/**
	 * Getters/Setters generated using eclipse
	 * 
	 * @param i Instability value
	 */
	public void setI(double i) {
		this.i = i;
	}
}
