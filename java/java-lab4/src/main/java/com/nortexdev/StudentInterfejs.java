package com.nortexdev;

import java.util.ArrayList;

// Jedyna różnica w implementacji interfejsu od klasy abstrakcyjnej
// jest użyte słówko "implements" zamiast "extends".
// Poza tym wszystko działa tak samo i może być wywołana przez
// `new StudentInterfejs()`.
public class StudentInterfejs implements OsobaInterfejs {
	ArrayList<Double> grades = new ArrayList<>();

	public StudentInterfejs() {
		grades.add(5d);
		grades.add(4d);
		grades.add(3d);
		grades.add(3.5d);
		grades.add(3d);
	}

	// ...
	
	@Override
	public String getFirstName() {
		return "Piotr";
	}

	@Override
	public String getLastName() {
		return "Mazurkiewicz";
	}

	public double getAverage() {
		double sum = 0;
		for (Double grade : grades) {
			sum += grade;
		}
		return sum / grades.size();
	}
}
