package com.github.thorbenkuck.jwr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAccess<T> {

	private final List<WeightedElement> elements = new ArrayList<>();
	private final Random random;
	private final double defaultWeight;

	public RandomAccess(double defaultWeight, Random random) {
		this.defaultWeight = defaultWeight;
		this.random = random;
	}

	public RandomAccess(Random random) {
		this(1.0, random);
	}

	public RandomAccess(double defaultWeight) {
		this(defaultWeight, new Random());
	}

	public RandomAccess() {
		this(1.0);
	}

	public RandomAccess<T> add(T t) {
		return add(t, defaultWeight);
	}

	public RandomAccess<T> add(T t, double weight) {
		elements.add(new WeightedElement(t, weight));
		return this;
	}

	public T next() {
		double totalWeight = 0.0d;
		for (WeightedElement i : elements) {
			totalWeight += i.weight;
		}

		int randomIndex = -1;
		double randomDouble = random.nextDouble() * totalWeight;

		for (int i = 0; i < elements.size(); ++i) {
			randomDouble -= elements.get(i).weight;
			if (randomDouble <= 0.0d) {
				randomIndex = i;
				break;
			}
		}

		WeightedElement myRandomItem = elements.get(randomIndex);
		return myRandomItem.t;
	}

	public List<T> next(int amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Cannot next 0 or less elements from this RandomAccess object");
		}

		final List<T> elements = new ArrayList<>();

		for(int i = 0 ; i < amount ; i++) {
			elements.add(next());
		}

		return elements;
	}

	private class WeightedElement {

		private final T t;
		private final double weight;

		WeightedElement(T t, double weight) {
			this.t = t;
			this.weight = weight;
		}
	}
}
