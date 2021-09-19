package ast;

import java.util.ArrayList;

public class StatementList {

    public StatementList(ArrayList<Statement> v) {
        this.v = v;
    }

    public void genC( PW pw ) {

      for( Statement s : v ) {
          s.genC(pw);
      }
    }

    private ArrayList<Statement> v;
}
