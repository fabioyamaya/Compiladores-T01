package ast;

public class WhileStat extends Statement {
	public WhileStat(StatementList statList, Expr expr) {
		super();
		this.statList = statList;
		this.expr = expr;
	}
	
	@Override
	public void genC(PW pw) {
		pw.print("while (");
		expr.genC(pw);
		pw.print(") {");
		pw.add();
		statList.genC(pw);
		pw.sub();
		pw.print("}");
	}
	
	private StatementList statList;
	private Expr expr;
	
}
