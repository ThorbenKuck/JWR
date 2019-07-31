package com.github.thorbenkuck.jwr;

import java.util.List;

public class RandomAccessTest {

	public static void main(String[] args) {
		RandomAccess<TestObject> randomAccess = new RandomAccess<>();

		randomAccess.add(new TestObject("1"))
				.add(new TestObject("2"), 2.0)
				.add(new TestObject("3"), 3.0)
				.add(new TestObject("Selten"), 0.1);

		for(int i = 0 ; i < 100 ; i++) {
			List<TestObject> elements = randomAccess.next(10);
			System.out.println(elements);
		}
	}

}
