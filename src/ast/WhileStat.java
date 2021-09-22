package ast;

import java.util.Map;

public class WhileStat extends Statement {
	public WhileStat(StatementList statList, Expr expr) {
		super();
		this.statList = statList;
		this.expr = expr;
	}
	
	@Override
	public void genC(PW pw) {
		pw.print("while ");
		expr.genC(pw);
		pw.out.println(" {");
		pw.add();
		statList.genC(pw);
		pw.sub();
		pw.println("}");
	}

	@Override
	public void run(Map<String, Integer> memory) {
		while (expr.run(memory) != 0) {
			statList.run(memory);
		}
	}

	private StatementList statList;
	private Expr expr;
	
}
