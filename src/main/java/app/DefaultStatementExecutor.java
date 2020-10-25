package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jsqlparser.schema.Table;

public class DefaultStatementExecutor {

	private static final Logger LOG = LogManager.getLogger(DefaultTableVisitor.class);

	private final Map<Table, Set<CSVRecord>> tableRecord = new HashMap<>();

	private final List<DefaultConstraint> constraints = new ArrayList<>();

	private Table startTable;

	private DefaultTableVisitor defaultTableVisitor;

	public List<DefaultResultRecord> execute() {

		// find all constraints over individual tables only and remove records that do
		// not satisfy this
		// constraint

		// find constraints that apply
		constraints.stream().filter(c -> c.getTableB() == null).forEach(c -> {

			List<CSVRecord> killList = new ArrayList<CSVRecord>();

			Optional<Table> resolveTable = defaultTableVisitor.resolveTable(c.getTableA());
			if (!resolveTable.isPresent()) {
				return;
			}

			Set<CSVRecord> csvRecords = tableRecord.get(resolveTable.get());
			for (CSVRecord csvRecord : csvRecords) {

				Object value = csvRecord.get(c.getColumnA().getColumnName());
				String trimmed = StringUtils.strip((String) value, "\"");

				switch (c.getConstraintType()) {

				case IN:
					if (!c.getValues().contains(trimmed)) {
						killList.add(csvRecord);
					}
					break;

				default:
					if (!trimmed.equals(c.getValue())) {
						killList.add(csvRecord);
					}
					break;

				}
			}

			LOG.info("Removing ...");
			csvRecords.removeAll(killList);
			LOG.info("Removing done.");
		});

		List<DefaultResultRecord> result = new ArrayList<DefaultResultRecord>();

		// build the initial result set
		Iterable<CSVRecord> csvRecords = tableRecord.get(startTable);
		for (CSVRecord csvRecord : csvRecords) {

			DefaultResultRecord defaultResultRecord = new DefaultResultRecord();
			defaultResultRecord.setCsvRecord(csvRecord);
			defaultResultRecord.setTable(startTable);

			result.add(defaultResultRecord);
		}

		// recursion over all connected tables
		List<DefaultResultRecord> killList = new ArrayList<>();
		for (DefaultResultRecord resultRecord : result) {
			if (!extendResult(resultRecord)) {
				killList.add(resultRecord);
			}
		}

		result.removeAll(killList);

		return result;
	}

	private boolean extendResult(DefaultResultRecord resultRecord) {

		// find constraints that apply
		Optional<DefaultConstraint> findFirstOptional = constraints.stream().filter(c -> c.getTableB() != null)
				.filter(c -> {

					// resolve table from the constraint
					Optional<Table> resolveTable = defaultTableVisitor.resolveTable(c.getTableA());
					Optional<Table> resolveTableB = defaultTableVisitor.resolveTable(resultRecord.getTable());

					if (!resolveTable.isPresent()) {
						return false;
					}
					if (!resolveTableB.isPresent()) {
						return false;
					}

					// compare
					return resolveTable.get().equals(resolveTableB.get());
				}).findFirst();

		// if no matching constraint was found, return
		if (findFirstOptional == null || !findFirstOptional.isPresent()) {
			return true;
		}

		DefaultConstraint currentConstraint = findFirstOptional.get();

		// get the tableB records
		// resolve table from the constraint
		Optional<Table> resolveTable = defaultTableVisitor.resolveTable(currentConstraint.getTableB());

		boolean constrainedSatisfied = false;
		Set<CSVRecord> csvRecords = tableRecord.get(resolveTable.get());
		for (CSVRecord csvRecord : csvRecords) {

			// find value in the current record
			String name = currentConstraint.getColumnA().getColumnName();
			Object lhs = resultRecord.getCsvRecord().get(name);
			Object rhs = csvRecord.get(currentConstraint.getColumnB().getColumnName());

			// filter non-matching
			if (!lhs.equals(rhs)) {
				continue;
			}

			DefaultResultRecord child = new DefaultResultRecord();
			child.setCsvRecord(csvRecord);
			child.setTable(currentConstraint.getTableB());

			// recurse
			boolean sc = extendResult(child);

			if (sc) {
				// extend the result set
				resultRecord.getRecords().add(child);
			}

			constrainedSatisfied = true;
		}

		return constrainedSatisfied;
	}

	public Map<Table, Set<CSVRecord>> getTableRecord() {
		return tableRecord;
	}

	public List<DefaultConstraint> getConstraints() {
		return constraints;
	}

	public Table getStartTable() {
		return startTable;
	}

	public void setStartTable(Table startTable) {
		this.startTable = startTable;
	}

	public DefaultTableVisitor getDefaultTableVisitor() {
		return defaultTableVisitor;
	}

	public void setDefaultTableVisitor(DefaultTableVisitor defaultTableVisitor) {
		this.defaultTableVisitor = defaultTableVisitor;
	}

}
