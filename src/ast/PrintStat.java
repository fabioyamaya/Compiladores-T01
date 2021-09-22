package ast;

import java.util.Map;

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

	@Override
	public void run(Map<String, Integer> memory) {
		System.out.print(expr.run(memory));
	}

	private Expr expr;
}
