package ie.gmit.sw.measure;

import ie.gmit.sw.reflection.JarContent;

public interface Measurable {

	public void measure(JarContent cls);
	public double getResult();
}
