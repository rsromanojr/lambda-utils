/*
 * Copyright (c) 2015 Ross Romano
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
package com.lambdautils.util.function;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Test;

import com.lambdautils.util.function.Rethrowing;

/**
 * @author rsromanojr
 *
 */
public class RethrowingTest {
	@Test(expected = ClassNotFoundException.class)
	public void biConsumer_given_biConsumer_that_throws_rethrows_exception() throws Exception {
		Map<Integer,String> map = new HashMap<>();
	       map.put(1, "A");
	       map.put(2, "B");
	       map.put(3, "C");
		map.forEach(Rethrowing.biConsumer((k, v) -> {
			throw new ClassNotFoundException();
		}));
	}

	@Test
	public void biConsumer_given_biConsumer_that_doesNot_throw_does_nothing() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.forEach(Rethrowing.biConsumer((k, v) -> System.out.println("k = " + k + ", v= " + v)));
	}

	@Test(expected = ClassNotFoundException.class)
	public void biFunction_given_biFunction_that_throws_rethrows_exception() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.computeIfPresent(1, Rethrowing.biFunction((k, v) -> {
			throw new ClassNotFoundException();
		}));
	}

	@Test
	public void biFunction_given_biFunction_that_doesNot_throw_does_nothing() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.computeIfPresent(1, Rethrowing.biFunction((k, v) -> k + v));
	}

	@Test(expected = ClassNotFoundException.class)
	public void biPredicate_given_biPredicate_that_throws_rethrows_exception() throws Exception {
		TestingFunctions.biPredicate(
				Rethrowing.biPredicate((t, u) -> { throw new ClassNotFoundException(); }))
				.test("one", "two");
	}

	@Test
	public void biPredicate_given_biPredicate_that_doesNot_throw_does_nothing() throws Exception {
		TestingFunctions.biPredicate(Rethrowing.biPredicate((t, u) -> true ))
				.test("one", "two");
	}

	@Test(expected = ClassNotFoundException.class)
	public void binaryOperator_given_binaryOperator_that_throws_rethrows_exception() throws Exception {
		TestingFunctions.binaryOperator(
				Rethrowing.binaryOperator((t, u) -> { 
					throw new ClassNotFoundException(); 
					}))
				.apply("one", "two");
	}

	@Test
	public void binaryOperator_given_binaryOperator_that_doesNot_throw_does_nothing() throws Exception {
		TestingFunctions.binaryOperator(Rethrowing.binaryOperator((t,u) -> true ))
				.apply("one", "two");
	}

	@Test(expected = ClassNotFoundException.class)
	public void booleanSupplier_given_booleanSupplier_that_throws_rethrows_exception() throws Exception {
		TestingFunctions.booleanSupplier(
				Rethrowing.booleanSupplier(() -> { 
					throw new ClassNotFoundException(); 
					})).getAsBoolean();
	}

	@Test
	public void booleanSupplier_given_booleanSupplier_that_doesNot_throw_does_nothing() throws Exception {
		TestingFunctions.booleanSupplier(Rethrowing.booleanSupplier(() -> true )).getAsBoolean();
	}

	@Test(expected = ClassNotFoundException.class)
	public void consumer_given_consumer_that_throws_rethrows_exception() throws Exception {
		Stream.of("one", "two", "three").forEach((Rethrowing.consumer(t -> {
			throw new ClassNotFoundException();
		})));
	}

	@Test
	public void consumer_given_consumer_that_doesNot_throw_does_nothing() throws Exception {
		Stream.of("one", "two", "three").forEach((Rethrowing.consumer(System.out::println)));
	}
}
