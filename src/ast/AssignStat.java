package ast;

public class AssignStat extends Statement {

    public AssignStat( Variable v, Expr expr ) {
        this.v = v;
        this.expr = expr;
    }

    @Override
	public void genC( PW pw ) {
        pw.print(v.getName() + " = " );
        expr.genC(pw);
        pw.out.println(";");
    }
    private Variable v;
    private Expr expr;
}
