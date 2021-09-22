package ast;

import java.util.Map;

public class IfStat extends Statement {

	public IfStat(Expr expr, StatementList thenPart, StatementList elsePart) {
		this.expr = expr;
		this.thenPart = thenPart;
		this.elsePart = elsePart;
	}

	@Override
	public void genC(PW pw) {

		pw.print("if ( ");
		expr.genC(pw);
		pw.out.println(" ) { ");
		pw.add();
		thenPart.genC(pw);
		pw.sub();
		pw.println("}");
		if (elsePart != null) {
			pw.println("else {");
			pw.add();
			elsePart.genC(pw);
			pw.sub();
			pw.println("}");
		}
	}

	@Override
	public void run(Map<String, Integer> memory) {
		if (expr.run(memory) != 0) {
			thenPart.run(memory);
		} else if (elsePart != null) {
			elsePart.run(memory);
		}
	}

	private Expr expr;
	private StatementList thenPart, elsePart;
}