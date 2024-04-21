package com.nortexdev;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) {
//		przykladMin();
//		przykladFilter();
//		przykladSorted();
//		przykladMap();
//		przykladFlatMap();
//		przykladReduce();
		przykladParallelStream();
	}

	private static void przykladMin() {
		List<Integer> list = List.of(1, 5, 8, 4, 9, 10, 4);
		Stream<Integer> stream = list.stream();
		Integer min = stream.min(Integer::compare).get();
		System.out.println(min);
	}

	private static void przykladFilter() {
		List<Integer> list = List.of(1, 5, 8, 4, 9, 10, 4);
		Stream<Integer> stream = list.stream();
		stream.filter(x -> x % 2 == 0).forEach(System.out::println);
	}

	private static void przykladSorted() {
		Collection<Person> people = List.of(
			new Person("Kamil", 25),
			new Person("Kasia", 30),
			new Person("Piotr", 20),
			new Person("Ania", 35),
			new Person("Ania", 40)
		);
		people.stream()
			.sorted(Comparator.comparing(Person::getNick).thenComparing(Person::getAge)) // Sortuj
			.map(Person::toString) // Każdy obiekt osoby zamień na string
			.forEach(System.out::println); // Wyświetl zmapowane stringi
	}

	private static void przykladMap() {
		Collection<PunktXYZ> punktyXYZ = List.of(
			new PunktXYZ(1, 2, 3),
			new PunktXYZ(4, 5, 6),
			new PunktXYZ(7, 8, 9)
		);

		Collection<PunktXY> punktyXY = punktyXYZ.stream()
			.map(punktXYZ -> new PunktXY(punktXYZ.getX(), punktXYZ.getY()))
			.collect(Collectors.toList());

		for (PunktXY punktXY : punktyXY) {
			System.out.println(punktXY);
		}
	}

	private static void przykladFlatMap() {
		Group eagles = new Group("Eagles", Arrays.asList(
			new Person2("Adam"),
			new Person2("Piotr")
		));

		Group bikers = new Group("Bikers", Arrays.asList(
			new Person2("Mateusz"),
			new Person2("Henryk")
		));

		Arrays.asList(eagles, bikers)
			.stream()
			.flatMap(group -> group.getMembers().stream())
			.map(Person2::toString)
			.forEach(System.out::println);
	}

	private static void przykladReduce() {
		List<Integer> list = List.of(1, 5, 8, 4, 9, 10, 4);

		Stream<Integer> stream = list.stream();
		int sumResult = stream.reduce(0, (acc, cur) -> acc + cur);
		System.out.println(sumResult);

		stream = list.stream();
		int multiplicationResult = stream.reduce(1, (acc, cur) -> acc * cur);
		System.out.println(multiplicationResult);
	}

	private static void przykladParallelStream() {
		List<String> uuids = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			uuids.add(UUID.randomUUID().toString());
		}

		long startTime, endTime, diff;

		startTime = System.currentTimeMillis();
		uuids.stream().sorted().collect(Collectors.toList());
		endTime = System.currentTimeMillis();
		diff = endTime - startTime;
		System.out.println("Czas z stream: " + diff + " ms");

		startTime = System.currentTimeMillis();
		uuids.parallelStream().sorted().collect(Collectors.toList());
		endTime = System.currentTimeMillis();
		diff = endTime - startTime;
		System.out.println("Czas z parallelStream: " + diff + " ms");
	}
}
