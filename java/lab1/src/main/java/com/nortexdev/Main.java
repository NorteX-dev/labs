package com.nortexdev;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
	public static void main(String[] args) {
		przyklad1();
		przyklad2();
		przyklad3();
		przyklad4();
		przyklad5();
	}

	// lambda z blokiem
	static void przyklad1() {
		Runnable runnable = () -> {
			System.out.println("Runnable");
		};
		runnable.run();
	}

	// strzałka lambda
	static void przyklad2() {
		Supplier<String> supplier = () -> "Supplier";
		System.out.println(supplier.get());
	}

	// wskazanie na metodę w obiekcie
	static void przyklad3() {
		Consumer<String> consumer = System.out::println;
		consumer.accept("Consumer");
	}

	static class Klasa {
		Klasa(String s) {
			System.out.println(s);
		}
	}

	// lambda ze wskazaniem konstruktora
	static void przyklad4() {
		Function<String, Klasa> function = Klasa::new;
		function.apply("Function");
	}

	static class Klasa2 {
		boolean method() {
			return true;
		}
	}

	// wskazanie na metodę w klasie
	static void przyklad5() {
		Predicate<Klasa2> predicate = Klasa2::method;
		predicate.test(new Klasa2());
	}
}
