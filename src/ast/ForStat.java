package ast;

public class ForStat extends Statement {

	public ForStat(Variable v, Expr startExpr, Expr endExpr, StatementList statList) {
		super();
		this.v = v;
		this.startExpr = startExpr;
		this.endExpr = endExpr;
		this.statList = statList;
	}

	@Override
	public void genC(PW pw) {

		pw.print("for (int ");
		pw.out.print(v.getName());
		pw.out.print("; ");
		pw.out.print(v.getName());
		pw.out.print(" = ");
		startExpr.genC(pw);
		pw.out.print("; ");
		pw.out.print(v.getName());
		pw.out.print(" <= ");
		endExpr.genC(pw);
		pw.out.println(") {");
		pw.add();
		statList.genC(pw);
		pw.sub();
		pw.println("}");
	}

	private Variable v;
	private Expr startExpr;
	private Expr endExpr;
	private StatementList statList;
}
