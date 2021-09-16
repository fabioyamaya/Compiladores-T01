package ast;

public class VariableExpr extends Expr {

    public VariableExpr( Variable v ) {
        this.v = v;
    }

    @Override
	public void genC( PW pw ) {
        pw.out.print( v.getName() );
    }


    private Variable v;
}