package io.brunovargas.zenvia.test001.roman.formatter.enumeration;

public enum RomanDigits {

	I('I', 1), V('V', 5, I), X('X', 10, I), L('L', 50, X), C('C', 100, X), D('D', 500, C), M('M', 1000, C);

	private char symbol;
	private int value;
	private RomanDigits preceding;

	private RomanDigits(char symbol, int value, RomanDigits preceding) {
		this.symbol = symbol;
		this.value = value;
		this.preceding = preceding;
	}

	private RomanDigits(char symbol, int value) {
		this.symbol = symbol;
		this.value = value;
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public RomanDigits getPreceding() {
		return preceding;
	}

	public void setPreceding(RomanDigits preceding) {
		this.preceding = preceding;
	}

	public String getPrecedingSymbol() {
		return new StringBuilder().append(this.preceding.getSymbol()).append(this.symbol).toString();
	}

	public int getPrecedingValue() {
		return this.value - this.preceding.value;
	}
}
