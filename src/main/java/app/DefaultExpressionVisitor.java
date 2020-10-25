package app;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jsqlparser.expression.DateValue;
import net.sf.jsqlparser.expression.DoubleValue;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.JdbcNamedParameter;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.TimeValue;
import net.sf.jsqlparser.expression.TimestampValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;

public class DefaultExpressionVisitor extends ExpressionVisitorAdapter {

	private static final Logger LOG = LogManager.getLogger(DefaultExpressionVisitor.class);

	private List<DefaultConstraint> constraints = new ArrayList<>();

	DefaultConstraint defaultConstraint = null;

	@Override
	public void visit(AndExpression expr) {
		LOG.info("AND");
		super.visit(expr);
	}

	@Override
	public void visit(OrExpression expr) {
		LOG.info("OR");
		super.visit(expr);
	}

	@Override
	public void visit(EqualsTo expr) {
		LOG.info("EQUALS");

		defaultConstraint = new DefaultConstraint();
		defaultConstraint.setConstraintType(ConstraintType.EQUALS);
		constraints.add(defaultConstraint);

		super.visit(expr);
	}

	@Override
	public void visit(GreaterThan expr) {
		LOG.info("GREATER THEN");

		defaultConstraint = new DefaultConstraint();
		defaultConstraint.setConstraintType(ConstraintType.GT);
		constraints.add(defaultConstraint);

		super.visit(expr);
	}

	@Override
	public void visit(GreaterThanEquals expr) {
		LOG.info("GreaterThanEquals");

		defaultConstraint = new DefaultConstraint();
		defaultConstraint.setConstraintType(ConstraintType.GTE);
		constraints.add(defaultConstraint);

		super.visit(expr);
	}

	@Override
	public void visit(InExpression expr) {

		defaultConstraint = new DefaultConstraint();
		defaultConstraint.setConstraintType(ConstraintType.IN);
		constraints.add(defaultConstraint);

		super.visit(expr);
	}

	@Override
	public void visit(Column column) {
		LOG.info(column);
		if (defaultConstraint.getColumnA() == null) {
			defaultConstraint.setColumnA(column);
			defaultConstraint.setTableA(column.getTable());
		} else if (defaultConstraint.getColumnB() == null) {
			defaultConstraint.setColumnB(column);
			defaultConstraint.setTableB(column.getTable());
		}
	}

	@Override
	public void visit(JdbcParameter parameter) {
		LOG.info(parameter);
	}

	@Override
	public void visit(JdbcNamedParameter parameter) {
		LOG.info(parameter);
	}

	@Override
	public void visit(DoubleValue value) {
		LOG.info(value);
	}

	@Override
	public void visit(LongValue value) {
		LOG.info(value);

		switch (defaultConstraint.getConstraintType()) {

		case IN:
			defaultConstraint.getValues().add(value);
			break;

		default:
			defaultConstraint.setValue(value.toString());

		}
	}

	@Override
	public void visit(DateValue value) {
		LOG.info(value);
	}

	@Override
	public void visit(TimeValue value) {
		LOG.info(value);
	}

	@Override
	public void visit(TimestampValue value) {
		LOG.info(value);
	}

	@Override
	public void visit(StringValue stringValue) {
		switch (defaultConstraint.getConstraintType()) {

		case IN:
			defaultConstraint.getValues().add(stringValue.getValue());
			break;

		default:
			defaultConstraint.setValue(stringValue.getValue());

		}
	}

	public List<DefaultConstraint> getConstraints() {
		return constraints;
	}
}
