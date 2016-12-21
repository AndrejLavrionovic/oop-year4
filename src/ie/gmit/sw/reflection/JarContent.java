package ie.gmit.sw.reflection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author g00196984
 * @version v-1.0
 * 
 * JarContent container for all classes
 * that are retrieved from provided jar package
 * 
 * Consist of List delegated methods
 *
 */
public class JarContent{

	private List<Class> cls = new ArrayList<Class>();

	public int numberOfClasses() {
		return cls.size();
	}

	public boolean isEmpty() {
		return cls.isEmpty();
	}

	public boolean addClass(Class e) {
		return cls.add(e);
	}

	public boolean removeClass(Object o) {
		return cls.remove(o);
	}

	public void removeAllClasses() {
		cls.clear();
	}

	public Class getClass(int index) {
		return cls.get(index);
	}
}
