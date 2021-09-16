package ast;

public class NumberExpr extends Expr {

	public NumberExpr(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public void genC(PW pw) {
		pw.out.print(value);
	}
	
	private int value;
}
