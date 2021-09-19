package ast;

public class PrintStat extends Statement {
	
	public PrintStat(Expr expr) {
		super();
		this.expr = expr;
	}

	
	@Override
	public void genC(PW pw) {
		pw.print("printf(%d, ");
		expr.genC(pw);
		pw.print(");");
	}
	
	private Expr expr;
}
