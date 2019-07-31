package com.github.thorbenkuck.jwr;

public class TestObject {

	private final String foo;

	public TestObject(String foo) {
		this.foo = foo;
	}

	public String getFoo() {
		return foo;
	}

	@Override
	public String toString() {
		return foo;
	}
}
