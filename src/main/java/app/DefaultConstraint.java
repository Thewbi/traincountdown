package app;

import java.util.ArrayList;
import java.util.List;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

/**
 * Allows to model two types of constraints. Either a constraint on a single
 * table (e.g. r.route_short_name = "U12E") or a constraint between two tables
 * (e.g. r.route_id = t.route_id)
 */
public class DefaultConstraint {

	private Table tableA;

	private Table tableB;

	private Column columnA;

	private Column columnB;

	/**
	 * if only tableA and columnA are set, the constraint describes a fixed value on
	 * columnA in tableA. That fixed value is contained in the value member
	 * variable. e.g. r.route_short_name = "U12E" as opposed to r.route_id =
	 * t.route_id
	 */
	private String value;

	private List<Object> values = new ArrayList<>();

	private ConstraintType constraintType;

	public Table getTableA() {
		return tableA;
	}

	public void setTableA(Table tableA) {
		this.tableA = tableA;
	}

	public Table getTableB() {
		return tableB;
	}

	public void setTableB(Table tableB) {
		this.tableB = tableB;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Column getColumnA() {
		return columnA;
	}

	public void setColumnA(Column columnA) {
		this.columnA = columnA;
	}

	public Column getColumnB() {
		return columnB;
	}

	public void setColumnB(Column columnB) {
		this.columnB = columnB;
	}

	public ConstraintType getConstraintType() {
		return constraintType;
	}

	public void setConstraintType(ConstraintType constraintType) {
		this.constraintType = constraintType;
	}

	public List<Object> getValues() {
		return values;
	}

}
