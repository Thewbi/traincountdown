package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.StatementVisitorAdapter;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.ParenthesisFromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;

public class DefaultTableVisitor extends StatementVisitorAdapter implements FromItemVisitor, SelectVisitor {

	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(DefaultTableVisitor.class);

	private final Map<String, Table> tables = new HashMap<>();

	@Override
	public void visit(PlainSelect plainSelect) {
		if (plainSelect.getFromItem() != null) {
			plainSelect.getFromItem().accept(this);
		}

		if (CollectionUtils.isNotEmpty(plainSelect.getJoins())) {
			for (Join join : plainSelect.getJoins()) {

				Table table = (Table) join.getRightItem();

				tables.put(table.getName(), table);
			}
		}
	}

	@Override
	public void visit(Select select) {
		select.getSelectBody().accept(this);
	}

	@Override
	public void visit(Table table) {
		tables.put(table.getName(), table);
	}

	@Override
	public void visit(SubSelect subSelect) {
		// Auto-generated method stub
	}

	@Override
	public void visit(SubJoin subjoin) {
		// Auto-generated method stub
	}

	@Override
	public void visit(LateralSubSelect lateralSubSelect) {
		// Auto-generated method stub
	}

	@Override
	public void visit(ValuesList valuesList) {
		// Auto-generated method stub
	}

	@Override
	public void visit(TableFunction tableFunction) {
		// Auto-generated method stub
	}

	@Override
	public void visit(ParenthesisFromItem aThis) {
		// Auto-generated method stub
	}

	@Override
	public void visit(SetOperationList setOpList) {
		// Auto-generated method stub
	}

	@Override
	public void visit(WithItem withItem) {
		// Auto-generated method stub
	}

	public Map<String, Table> getTables() {
		return tables;
	}

	public Optional<Table> resolveTable(Table table) {

		if (tables.values().contains(table)) {
			return Optional.of(table);
		}
		String name = table.getName();
		return tables.values().stream().filter(t -> t.getAlias().getName().endsWith(name)).findFirst();
	}

}
