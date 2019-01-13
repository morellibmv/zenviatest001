import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringRegularExpression.matchesRegex;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Rule;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import io.brunovargas.zenvia.test001.roman.formatter.RomanFormatter;
import io.brunovargas.zenvia.test001.roman.formatter.enumeration.RomanDigits;

@RunWith(Theories.class)
public class RomanFormatterTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@DataPoints("invalidValues")
	public static List<Integer> invalidPositiveValues = IntStream.rangeClosed(4000, 100000).boxed()
			.collect(Collectors.toList());

	@DataPoints("invalidValues")
	public static List<Integer> invalidNonPositiveValues = IntStream.rangeClosed(-100000, 0).boxed()
			.collect(Collectors.toList());

	@DataPoints("validValues")
	public static List<Integer> validValues = IntStream.rangeClosed(1, 3999).boxed().collect(Collectors.toList());

	@DataPoints("enumValues")
	public static RomanDigits[] digits = RomanDigits.values();

	@Theory
	public void testHigherValues(@FromDataPoints("invalidValues") int value) {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Value must be an integer between 1 and 3999 inclusive");
		RomanFormatter romanFormatter = new RomanFormatter();
		romanFormatter.format(value);
	}

	@Theory
	public void testValidValues(@FromDataPoints("validValues") int value) {
		RomanFormatter romanFormatter = new RomanFormatter();
		String result = romanFormatter.format(value);
		assertThat(result, matchesRegex("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"));
	}

	@Theory
	public void testSingleDigits(@FromDataPoints("enumValues") RomanDigits romanDigit) {
		RomanFormatter romanFormatter = new RomanFormatter();
		String romanValue = romanFormatter.format(romanDigit.getValue());
		assertThat(romanValue, equalTo(new StringBuilder().append(romanDigit.getSymbol()).toString()));
	}
	
	@Theory
	public void testPrecedingValues(@FromDataPoints("enumValues") RomanDigits romanDigit) {
		RomanFormatter romanFormatter = new RomanFormatter();
		if(romanDigit.getPreceding() != null){
			String romanValue = romanFormatter.format(romanDigit.getValue() - romanDigit.getPreceding().getValue());
			assertThat(romanValue, equalTo(new StringBuilder().append(romanDigit.getPreceding().getSymbol()).append(romanDigit.getSymbol()).toString()));
		}
	}

}
