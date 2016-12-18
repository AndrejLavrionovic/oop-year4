package ie.gmit.sw;

import java.util.Calendar;

public class Person {
	
	private String name;
	private int age;
	private Calendar dob;
	
	
	public Person() {
		super();
	}

	public Person(String name, int age, Calendar dob) {
		super();
		this.name = name;
		this.age = age;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	
	
}
