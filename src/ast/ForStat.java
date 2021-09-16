package ast;

public class ForStat extends Statement {

	public ForStat(Variable v, Expr startExpr, Expr endExpr, StatementList statList) {
		super();
		this.v = v;
		this.startExpr = startExpr;
		this.endExpr = endExpr;
	}

	@Override
	public void genC(PW pw) {

		pw.print("for (int ");
		pw.print(v.getName());
		pw.print("; ");
		pw.print(v.getName());
		pw.print(" = ");
		startExpr.genC(pw);
		pw.print("; ");
		pw.print(v.getName());
		pw.print(" <= ");
		endExpr.genC(pw);
		pw.print(") {");
		pw.add();
		statList.genC(pw);
		pw.sub();
		pw.print("}");
	}

	private Variable v;
	private Expr startExpr;
	private Expr endExpr;
	private StatementList statList;
}
