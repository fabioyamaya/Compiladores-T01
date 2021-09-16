package main;

import java.util.ArrayList;

import ast.*;

public class Program {

    public Program( ArrayList<Variable> arrayVariable,
                    StatementList statementList ) {
        this.arrayVariable = arrayVariable;
        this.statementList = statementList;
    }

    public void genC( PW pw ) {

        pw.out.println("#include <stdio.h>");
        pw.out.println();
        pw.println("void main() {");

        pw.add();
          // generate code for the declaration of variables
        for( Variable v : arrayVariable )
            pw.println("Int" +
                " " + v.getName() + ";" );


        pw.out.println("");
        statementList.genC(pw);
        pw.sub();
        pw.out.println("}");
    }

    private ArrayList<Variable> arrayVariable;
    private StatementList statementList;
}