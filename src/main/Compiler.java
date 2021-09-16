package main;

import java.io.PrintWriter;

import ast.CompilerError;
import lexer.Lexer;
import lexer.Symbol;

public class Compiler {

	public void compile(char[] input, PrintWriter outError) {

		error = new CompilerError(outError);
		lexer = new Lexer(input, error);
		error.setLexer(lexer);

		lexer.nextToken();
		program();

		if (lexer.token != Symbol.EOF)
			error.signal("EOF expected");

		return;
	}

	private void program() {
		varList();
		while (lexer.token != Symbol.EOF) {
			stat();
		}
	}

	private void varList() {
		while (lexer.token == Symbol.VAR) {
			lexer.nextToken();
			if (lexer.token != Symbol.INTEGER) {
				error.signal("Type of variable expected");
			}
			lexer.nextToken();
			ident();
			if (lexer.token != Symbol.SEMICOLON) {
				error.signal("Missing semicolon");
			}
			lexer.nextToken();
		}
		return;
	}

	private void stat() {
		switch (lexer.token) {
		case IDENT:
			assignStat();
			break;
		case IF:
			ifStat();
			break;
		case FOR:
			forStat();
			break;
		case PRINT:
			printStat();
			break;
		case PRINTLN:
			printLnStat();
			break;
		case WHILE:
			whileStat();
			break;
		default:
			error.signal("Statement expected");
		}
		return;
	}

	private void ident() {
		if (lexer.token != Symbol.IDENT) {
			error.signal("Identifier of variable expected");
		}
		lexer.nextToken();
		return;
	}

	private void assignStat() {
		lexer.nextToken();
		if (lexer.token != Symbol.ASSIGN) {
			error.signal("= expected");
		}
		lexer.nextToken();
		expr();
		if (lexer.token != Symbol.SEMICOLON) {
			error.signal("; expected");
		}
		lexer.nextToken();
		return;
	}

	private void ifStat() {
		lexer.nextToken();
		expr();
		statList();
		if (lexer.token != Symbol.ELSE) {
			return;
		}
		lexer.nextToken();
		statList();
		return;
	}

	private void forStat() {
		lexer.nextToken();

		ident();
		if (lexer.token != Symbol.IN) {
			error.signal("in keyword expected");
		}
		lexer.nextToken();
		expr();

		if (lexer.token != Symbol.DOUBLEDOTS) {
			error.signal(".. keyword expected");
		}
		lexer.nextToken();
		expr();

		statList();
		return;
	}

	private void printStat() {
		lexer.nextToken();
		expr();
		if (lexer.token != Symbol.SEMICOLON) {
			error.signal("; expected");
		}
		lexer.nextToken();
	}

	private void printLnStat() {
		lexer.nextToken();
		expr();
		if (lexer.token != Symbol.SEMICOLON) {
			error.signal("; expected");
		}
		lexer.nextToken();
		return;
	}

	private void statList() {
		if (lexer.token != Symbol.LEFTCURL) {
			error.signal("{ expected");
		}
		lexer.nextToken();
		while (lexer.token != Symbol.RIGHTCURL) {
			stat();
		}
		lexer.nextToken();
		return;
	}

	private void whileStat() {
		lexer.nextToken();
		expr();
		statList();
	}

	private void expr() {
		andExpr();
		if (lexer.token == Symbol.OR) {
			lexer.nextToken();
			andExpr();
		}

		return;
	}

	private void andExpr() {
		relExpr();
		if (lexer.token == Symbol.AND) {
			lexer.nextToken();
			relExpr();
		}
		return;
	}

	private void relExpr() {
		addExpr();

		if (lexer.token == Symbol.LT || lexer.token == Symbol.LE || lexer.token == Symbol.GT || lexer.token == Symbol.GE
				|| lexer.token == Symbol.EQ || lexer.token == Symbol.NEQ) {
			lexer.nextToken();
			addExpr();
		}

		return;
	}

	private void addExpr() {
		multExpr();

		while (lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS) {
			lexer.nextToken();
			multExpr();
		}

		return;
	}

	private void multExpr() {
		simpleExpr();
		while (lexer.token == Symbol.MULT || lexer.token == Symbol.DIV || lexer.token == Symbol.REMAINDER) {
			lexer.nextToken();
			simpleExpr();
		}
		return;
	}

	private void simpleExpr() {
		switch (lexer.token) {
		case NUMBER:
			lexer.nextToken();
			break;
		case LEFTPAR:
			expr();
			if (lexer.token != Symbol.RIGHTPAR) {
				error.signal(") expected");
			}
			break;
		case NOT:
			simpleExpr();
			break;
		case PLUS:
			simpleExpr();
			break;
		case MINUS:
			simpleExpr();
			break;
		case IDENT:
			ident();
			break;
		default:
			error.signal("Expression expected");
		}
		return;
	}

	// private Hashtable<String, Variable> symbolTable;
	private Lexer lexer;
	private CompilerError error;

}
