package io.brunovargas.zenvia.test001.roman.formatter;

import static io.brunovargas.zenvia.test001.roman.validation.IntegerValidator.biggerEqualThan;
import static io.brunovargas.zenvia.test001.roman.validation.IntegerValidator.isNonPositive;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import io.brunovargas.zenvia.test001.roman.formatter.enumeration.RomanDigits;
import io.brunovargas.zenvia.test001.roman.validation.IntegerValidator;

public class RomanFormatter implements IntegerFormatter {

	private static final int MAX_VALUE = 4000;

	private static final int MIN_VALUE = 0;

	private static final String EMPTY_VALUE = "";

	@Override
	public String format(int value) throws IllegalArgumentException {

		if (isNonPositive().or(biggerEqualThan(MAX_VALUE)).test(value)) {
			throw new IllegalArgumentException("Value must be an integer between 1 and 3999 inclusive");
		}

		if (IntegerValidator.equalThan(MIN_VALUE).test(value)) {
			return EMPTY_VALUE;
		}

		int auxiliarValue = value;

		StringBuilder result = new StringBuilder();

		RomanDigits[] digits = RomanDigits.values();
		Arrays.sort(digits, (a, b) -> b.getValue() - a.getValue());

		for (RomanDigits digit : digits) {
			result.append(StringUtils.repeat(digit.getSymbol(), auxiliarValue / digit.getValue()));
			auxiliarValue = auxiliarValue % digit.getValue();
			if (digit.getPreceding() != null) {
				result.append(
						StringUtils.repeat(digit.getPrecedingSymbol(), auxiliarValue / digit.getPrecedingValue()));
				auxiliarValue = auxiliarValue % digit.getPrecedingValue();
			}
		}
		return result.toString();
	}

}
