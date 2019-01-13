package io.brunovargas.zenvia.test001.roman.validation;

import java.util.function.IntPredicate;

public interface IntegerValidator extends IntPredicate {

	static IntegerValidator isNonNegative() {
		return value -> value >= 0;
	}

	static IntegerValidator isNonPositive() {
		return value -> value <= 0;
	}

	static IntegerValidator isPositive() {
		return value -> value > 0;
	}

	static IntegerValidator isNegative() {
		return value -> value < 0;
	}

	static IntegerValidator biggerThan(int biggerThanValue) {
		return value -> value > biggerThanValue;
	}

	static IntegerValidator minorThan(int minorThanValue) {
		return value -> value < minorThanValue;
	}

	static IntegerValidator minorEqualThan(int minorEqualThanValue) {
		return value -> value <= minorEqualThanValue;
	}
	
	static IntegerValidator biggerEqualThan(int biggerEqualThanValue){
		return value -> value >= biggerEqualThanValue;
	}
	
	static IntegerValidator equalThan(int equalThanValue){
		return value -> value == 0;
	}
}
