package ast;

import lexer.Symbol;

import java.util.Map;

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
        if(expr.getClass() == UnaryExpr.class) {
            pw.out.print("(");
            expr.genC(pw);
            pw.out.print(")");
        } else {
            expr.genC(pw);
        }
    }

    @Override
    public Integer run(Map<String, Integer> memory) {
        switch ( op ) {
            case PLUS :
                return +expr.run(memory);
            case MINUS :
                return -expr.run(memory);
            case NOT :
                return expr.run(memory) == 0 ? 1 : 0;
            default:
                System.out.println("Operação " + op.toString() + " não implementada");
                return 0;
        }
    }


    private Expr expr;
    private Symbol op;
}
