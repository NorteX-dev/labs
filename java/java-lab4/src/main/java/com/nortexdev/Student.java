package com.nortexdev;

import java.util.ArrayList;

public class Student extends Osoba {
	ArrayList<Double> grades = new ArrayList<>();

	public Student() {
		super();
		grades.add(5d);
		grades.add(4d);
		grades.add(3d);
		grades.add(3.5d);
		grades.add(3d);
	}

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
