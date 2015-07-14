package com.ilkertatar.GameOf2048;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * @author ilker Tatar
 * 
 */
public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TileFunctionsImplTester.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}
}
