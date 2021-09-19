package ast;

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
	
	private StatementList statList;
	private Expr expr;
	
}
