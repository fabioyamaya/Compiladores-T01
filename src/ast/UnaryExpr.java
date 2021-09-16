package ast;

import lexer.Symbol;

public class UnaryExpr extends Expr {

    public UnaryExpr( Expr expr, Symbol op ) {
        this.expr = expr;
        this.op = op;
    }

    @Override
	public void genC( PW pw ) {
        switch ( op ) {
            case PLUS :
              pw.out.print("+");
              break;
            case MINUS :
              pw.out.print("-");
              break;
            case NOT :
              pw.out.print("!");
              break;
        }
        expr.genC(pw);
    }


    private Expr expr;
    private Symbol op;
}
