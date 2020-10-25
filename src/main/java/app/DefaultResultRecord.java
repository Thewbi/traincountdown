package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

public class DefaultResultRecord {

	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(DefaultResultRecord.class);

	private Table table;

	private List<DefaultResultRecord> records = new ArrayList<>();

	private CSVRecord csvRecord;

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n");
		int indent = 0;

		recurse(stringBuilder, indent, null);

		return stringBuilder.toString();
	}

	private void recurse(StringBuilder stringBuilder, int indent, Map<String, Expression> map) {

		// indent and output
		for (int i = 0; i < indent; i++) {
			stringBuilder.append("  ");
		}
		stringBuilder.append(csvRecord);
		stringBuilder.append("\n");

		// recurse
		for (DefaultResultRecord defaultResultRecord : records) {
			defaultResultRecord.recurse(stringBuilder, indent + 1, map);
		}
	}

	public String select(Map<String, Expression> map, DefaultTableVisitor defaultTableVisitor) {

		StringBuilder stringBuilder = new StringBuilder();

		recurseSelect(stringBuilder, map, defaultTableVisitor);

		return stringBuilder.toString();
	}

	private void recurseSelect(StringBuilder stringBuilder, Map<String, Expression> map,
			DefaultTableVisitor defaultTableVisitor) {

		// check if any column of this table is selected
		for (Expression expression : map.values()) {

			Column selectedColumn = (Column) expression;
			String selectedColumnName = selectedColumn.getColumnName();

			// no table identifiable within the SQL statement for this column so look
			// through
			// this table's columns and match by name
			if (csvRecord.isMapped(selectedColumnName)) {
				stringBuilder.append(csvRecord.get(selectedColumnName)).append(" ");
			}

//			if (selectedColumn.getTable() != null) {
//				
//				Table table = selectedColumn.getTable();
//				
//				Optional<Table> resolvedTableOptional = defaultTableVisitor.resolveTable(table);
//				Table resolvedTable = resolvedTableOptional.get();
//				
//				if (resolvedTable.equals(getTable())) {
//					LOG.info("Match by table!");
//				}
//				
//			} else {
//				
//				// no table identifiable within the SQL statement for this column so look through
//				// this table's columns and match by name
//				if (csvRecord.isMapped(selectedColumnName)) {
//					stringBuilder.append(csvRecord.get(selectedColumnName)).append(" ");
//				}
//				
//			}
		}

		// recurse
		if (CollectionUtils.isEmpty(records)) {
			stringBuilder.append("\n");
		} else {
			for (DefaultResultRecord defaultResultRecord : records) {
				defaultResultRecord.recurseSelect(stringBuilder, map, defaultTableVisitor);
			}
		}

	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<DefaultResultRecord> getRecords() {
		return records;
	}

	public CSVRecord getCsvRecord() {
		return csvRecord;
	}

	public void setCsvRecord(CSVRecord csvRecord) {
		this.csvRecord = csvRecord;
	}

}
