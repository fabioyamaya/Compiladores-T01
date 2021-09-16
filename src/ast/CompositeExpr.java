package ast;

import lexer.Symbol;

public class CompositeExpr extends Expr {

	public CompositeExpr(Expr left, Symbol oper, Expr right) {
		this.left = left;
		this.oper = oper;
		this.right = right;
	}

	@Override
	public void genC(PW pw) {
		pw.out.print("(");
		left.genC(pw);
		pw.out.print(" " + oper.toString() + " ");
		right.genC(pw);
		pw.out.print(")");
	}

	private Expr left, right;
	private Symbol oper;
}
