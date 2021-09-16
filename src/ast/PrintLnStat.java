package ast;

public class PrintLnStat extends Statement {
	
	public PrintLnStat(Expr expr) {
		super();
		this.expr = expr;
	}
	
	@Override
	public void genC(PW pw) {
		pw.print("printf(\"%d\n\", ");
		expr.genC(pw);
		pw.print(");");
	}
	
	private Expr expr;
}

