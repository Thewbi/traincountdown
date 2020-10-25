package app;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitorAdapter;

public class Main {
	
	private static final Logger LOG = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws IOException, JSQLParserException {
		LOG.info("Application Start: {}", new Date());
		
//		final String statementAsString = 
//				"SELECT * FROM tab1";
		
//		final String statementAsString = "SELECT * FROM Customers ORDER BY Country, CustomerName";
		
//		final String statementAsString = "SELECT * FROM Customers ORDER BY Country";
		
//		final String statementAsString = 
//				"SELECT col1 AS a, col2 AS b, col3 AS c, col4 FROM table WHERE col1 = 10 AND col2 = 20 AND col3 = 30";
		
//		final String statementAsString = 
//				"SELECT t.* FROM routes AS r, trips AS t, calendar AS c WHERE r.route_short_name = 'U12E' AND t.route_id = r.route_id AND c.service_id = t.service_id AND c.Monday = '1'";
		
//		final String statementAsString = 
//				"SELECT t.trip_id FROM routes AS r, trips AS t, calendar AS c WHERE r.route_short_name = 'U12E' AND r.route_id = t.route_id AND t.service_id = c.service_id AND c.monday = '1'";
//		
//		final String statementAsString = 
//				"SELECT t.trip_id FROM routes AS r, trips AS t, calendar AS c WHERE r.route_short_name = 'U12E' AND r.route_id = t.route_id AND t.service_id = c.service_id";
		
//		final String statementAsString = 
//				"SELECT t.trip_id FROM routes AS r, trips AS t, calendar AS c WHERE r.route_short_name = 'U12' AND r.route_id = t.route_id AND t.trip_headsign = 'Neckargröningen Remseck' AND t.service_id = c.service_id AND c.monday = '1'";
//		
//		final String statementAsString = 
//				"SELECT st.arrival_time FROM stop_times AS st WHERE st.trip_id IN ( 1,2,3 ) AND st.arrival_time > 'DateNow' ORDER BY st.arrival_time ASC";
		
//		final String statementAsString = 
//				"SELECT * FROM a AS a, b AS b WHERE a.A2 = b.B1";
		
//		final String statementAsString = 
//				"SELECT st.arrival_time, st.trip_id FROM stop_times AS st WHERE st.stop_id = 'de:08111:297:0:2'";
		
//		final String statementAsString = 
//				"SELECT st.arrival_time, st.trip_id "
//				+ "FROM stop_times AS st "
//				+ "WHERE st.stop_id = 'de:08111:297:0:2' AND st.trip_id = '422.T0.20-12-j20-1.2.R'";
		
//		final String statementAsString = 
//				"SELECT st.arrival_time, st.trip_id "
//				+ "FROM stop_times AS st "
//				+ "WHERE st.stop_id = 'de:08111:297:0:2' AND st.trip_id IN ('422.T0.20-12-j20-1.2.R', '396.T0.20-12-j20-1.2.R')";
		
		// monday
//		final String statementAsString = 
//				"SELECT st.arrival_time "
//				+ "FROM stop_times AS st "
//				+ "WHERE st.trip_id IN ('396.T0.20-12-j20-1.2.R','360.T0.20-12-j20-1.2.R','250.T0.20-12-j20-1.1.R','344.T0.20-12-j20-1.2.R','446.T0.20-12-j20-1.2.R','294.T0.20-12-j20-1.2.R','370.T0.20-12-j20-1.2.R','382.T0.20-12-j20-1.2.R','432.T0.20-12-j20-1.2.R','334.T0.20-12-j20-1.2.R','290.T0.20-12-j20-1.2.R','380.T0.20-12-j20-1.2.R','354.T0.20-12-j20-1.2.R','386.T0.20-12-j20-1.2.R','260.T0.20-12-j20-1.2.R','348.T0.20-12-j20-1.2.R','312.T0.20-12-j20-1.2.R','298.T0.20-12-j20-1.2.R','438.T0.20-12-j20-1.2.R','462.T0.20-12-j20-1.2.R','474.T0.20-12-j20-1.2.R','408.T0.20-12-j20-1.2.R','266.T0.20-12-j20-1.2.R','336.T0.20-12-j20-1.2.R','472.T0.20-12-j20-1.2.R','304.T0.20-12-j20-1.2.R','410.T0.20-12-j20-1.2.R','388.T0.20-12-j20-1.2.R','420.T0.20-12-j20-1.2.R','404.T0.20-12-j20-1.2.R','456.T0.20-12-j20-1.2.R','288.T0.20-12-j20-1.2.R','282.T0.20-12-j20-1.2.R','444.T0.20-12-j20-1.2.R','326.T0.20-12-j20-1.2.R','398.T0.20-12-j20-1.2.R','394.T0.20-12-j20-1.2.R','346.T0.20-12-j20-1.2.R','424.T0.20-12-j20-1.2.R','338.T0.20-12-j20-1.2.R','272.T0.20-12-j20-1.2.R','378.T0.20-12-j20-1.2.R','284.T0.20-12-j20-1.2.R','384.T0.20-12-j20-1.2.R','356.T0.20-12-j20-1.2.R','324.T0.20-12-j20-1.2.R','426.T0.20-12-j20-1.2.R','436.T0.20-12-j20-1.2.R','332.T0.20-12-j20-1.2.R','320.T0.20-12-j20-1.2.R','246.T0.20-12-j20-1.3.R','478.T0.20-12-j20-1.2.R','256.T0.20-12-j20-1.2.R','428.T0.20-12-j20-1.2.R','458.T0.20-12-j20-1.2.R','400.T0.20-12-j20-1.2.R','274.T0.20-12-j20-1.2.R','262.T0.20-12-j20-1.2.R','350.T0.20-12-j20-1.2.R','468.T0.20-12-j20-1.2.R','362.T0.20-12-j20-1.2.R','264.T0.20-12-j20-1.1.R','270.T0.20-12-j20-1.1.R','300.T0.20-12-j20-1.2.R','308.T0.20-12-j20-1.2.R','352.T0.20-12-j20-1.2.R','406.T0.20-12-j20-1.2.R','253.T0.20-12-j20-1.2.R','268.T0.20-12-j20-1.2.R','340.T0.20-12-j20-1.2.R','376.T0.20-12-j20-1.2.R','296.T0.20-12-j20-1.2.R','254.T0.20-12-j20-1.1.R','464.T0.20-12-j20-1.2.R','434.T0.20-12-j20-1.2.R','418.T0.20-12-j20-1.2.R','416.T0.20-12-j20-1.2.R','318.T0.20-12-j20-1.2.R','452.T0.20-12-j20-1.2.R','402.T0.20-12-j20-1.2.R','276.T0.20-12-j20-1.2.R','302.T0.20-12-j20-1.2.R','342.T0.20-12-j20-1.2.R','470.T0.20-12-j20-1.2.R','366.T0.20-12-j20-1.2.R','258.T0.20-12-j20-1.2.R','280.T0.20-12-j20-1.2.R','292.T0.20-12-j20-1.2.R','328.T0.20-12-j20-1.2.R','368.T0.20-12-j20-1.2.R','412.T0.20-12-j20-1.2.R','392.T0.20-12-j20-1.2.R','249.T0.20-12-j20-1.2.R','422.T0.20-12-j20-1.2.R','314.T0.20-12-j20-1.2.R','306.T0.20-12-j20-1.2.R','286.T0.20-12-j20-1.2.R','310.T0.20-12-j20-1.2.R','476.T0.20-12-j20-1.2.R','330.T0.20-12-j20-1.2.R','364.T0.20-12-j20-1.2.R','244.T0.20-12-j20-1.1.R','372.T0.20-12-j20-1.2.R','390.T0.20-12-j20-1.2.R','414.T0.20-12-j20-1.2.R','278.T0.20-12-j20-1.2.R','460.T0.20-12-j20-1.2.R','430.T0.20-12-j20-1.2.R','322.T0.20-12-j20-1.2.R','374.T0.20-12-j20-1.2.R','316.T0.20-12-j20-1.2.R','358.T0.20-12-j20-1.2.R','466.T0.20-12-j20-1.2.R') "
//				+ "AND st.stop_id = 'de:08111:297:0:2'";
		
		
//		// sunday
//		final String statementAsString = 
//				"SELECT st.arrival_time "
//				+ "FROM stop_times AS st "
//				+ "WHERE st.trip_id IN ('195.T3.20-12-j20-1.2.R','143.T3.20-12-j20-1.2.R','107.T3.20-12-j20-1.2.R','170.T3.20-12-j20-1.2.R','177.T3.20-12-j20-1.2.R','118.T3.20-12-j20-1.2.R','104.T3.20-12-j20-1.2.R','178.T3.20-12-j20-1.2.R','155.T3.20-12-j20-1.2.R','105.T3.20-12-j20-1.2.R','181.T3.20-12-j20-1.2.R','164.T3.20-12-j20-1.2.R','165.T3.20-12-j20-1.2.R','119.T3.20-12-j20-1.2.R','146.T3.20-12-j20-1.2.R','190.T3.20-12-j20-1.2.R','163.T3.20-12-j20-1.2.R','150.T3.20-12-j20-1.2.R','117.T3.20-12-j20-1.2.R','133.T3.20-12-j20-1.2.R','194.T3.20-12-j20-1.2.R','142.T3.20-12-j20-1.2.R','116.T3.20-12-j20-1.3.R','134.T3.20-12-j20-1.2.R','132.T3.20-12-j20-1.2.R','136.T3.20-12-j20-1.2.R','149.T3.20-12-j20-1.2.R','129.T3.20-12-j20-1.2.R','111.T3.20-12-j20-1.2.R','109.T3.20-12-j20-1.2.R','102.T3.20-12-j20-1.1.R','113.T3.20-12-j20-1.2.R','115.T3.20-12-j20-1.2.R','101.T3.20-12-j20-1.3.R','126.T3.20-12-j20-1.2.R','110.T3.20-12-j20-1.1.R','147.T3.20-12-j20-1.2.R','196.T3.20-12-j20-1.2.R','103.T3.20-12-j20-1.2.R','151.T3.20-12-j20-1.2.R','135.T3.20-12-j20-1.2.R','180.T3.20-12-j20-1.2.R','154.T3.20-12-j20-1.2.R','128.T3.20-12-j20-1.2.R','124.T3.20-12-j20-1.2.R','184.T3.20-12-j20-1.2.R','171.T3.20-12-j20-1.2.R','108.T3.20-12-j20-1.2.R','131.T3.20-12-j20-1.2.R','125.T3.20-12-j20-1.2.R','144.T3.20-12-j20-1.2.R','120.T3.20-12-j20-1.2.R','156.T3.20-12-j20-1.2.R','139.T3.20-12-j20-1.2.R','148.T3.20-12-j20-1.2.R','114.T3.20-12-j20-1.2.R','187.T3.20-12-j20-1.2.R','185.T3.20-12-j20-1.2.R','168.T3.20-12-j20-1.2.R','123.T3.20-12-j20-1.1.R','167.T3.20-12-j20-1.2.R','106.T3.20-12-j20-1.2.R','191.T3.20-12-j20-1.2.R','193.T3.20-12-j20-1.2.R','160.T3.20-12-j20-1.2.R','166.T3.20-12-j20-1.2.R','161.T3.20-12-j20-1.2.R','182.T3.20-12-j20-1.2.R','122.T3.20-12-j20-1.2.R','162.T3.20-12-j20-1.2.R','188.T3.20-12-j20-1.2.R','183.T3.20-12-j20-1.2.R','138.T3.20-12-j20-1.2.R','157.T3.20-12-j20-1.2.R','172.T3.20-12-j20-1.2.R','179.T3.20-12-j20-1.2.R','112.T3.20-12-j20-1.2.R','174.T3.20-12-j20-1.2.R','127.T3.20-12-j20-1.1.R','192.T3.20-12-j20-1.2.R','137.T3.20-12-j20-1.2.R','130.T3.20-12-j20-1.2.R','153.T3.20-12-j20-1.2.R','152.T3.20-12-j20-1.2.R','140.T3.20-12-j20-1.2.R','121.T3.20-12-j20-1.2.R','176.T3.20-12-j20-1.2.R','159.T3.20-12-j20-1.2.R','186.T3.20-12-j20-1.2.R','189.T3.20-12-j20-1.2.R','141.T3.20-12-j20-1.2.R','145.T3.20-12-j20-1.2.R','158.T3.20-12-j20-1.2.R','197.T3.20-12-j20-1.2.R') "
//				+ "AND st.stop_id = 'de:08111:297:0:2'";
		
		
		
		// monday
		final String statementAsString = 
				"SELECT st.arrival_time "
				+ "FROM stop_times AS st "
				+ "WHERE st.trip_id IN ('476.T0.20-12-j20-1.2.R','346.T0.20-12-j20-1.2.R','344.T0.20-12-j20-1.2.R','356.T0.20-12-j20-1.2.R','368.T0.20-12-j20-1.2.R','332.T0.20-12-j20-1.2.R','312.T0.20-12-j20-1.2.R','418.T0.20-12-j20-1.2.R','276.T0.20-12-j20-1.2.R','366.T0.20-12-j20-1.2.R','246.T0.20-12-j20-1.3.R','300.T0.20-12-j20-1.2.R','330.T0.20-12-j20-1.2.R','414.T0.20-12-j20-1.2.R','402.T0.20-12-j20-1.2.R','272.T0.20-12-j20-1.2.R','244.T0.20-12-j20-1.1.R','396.T0.20-12-j20-1.2.R','458.T0.20-12-j20-1.2.R','290.T0.20-12-j20-1.2.R','466.T0.20-12-j20-1.2.R','424.T0.20-12-j20-1.2.R','370.T0.20-12-j20-1.2.R','406.T0.20-12-j20-1.2.R','392.T0.20-12-j20-1.2.R','284.T0.20-12-j20-1.2.R','478.T0.20-12-j20-1.2.R','374.T0.20-12-j20-1.2.R','270.T0.20-12-j20-1.1.R','268.T0.20-12-j20-1.2.R','428.T0.20-12-j20-1.2.R','362.T0.20-12-j20-1.2.R','288.T0.20-12-j20-1.2.R','264.T0.20-12-j20-1.1.R','302.T0.20-12-j20-1.2.R','258.T0.20-12-j20-1.2.R','452.T0.20-12-j20-1.2.R','298.T0.20-12-j20-1.2.R','412.T0.20-12-j20-1.2.R','470.T0.20-12-j20-1.2.R','426.T0.20-12-j20-1.2.R','254.T0.20-12-j20-1.1.R','318.T0.20-12-j20-1.2.R','306.T0.20-12-j20-1.2.R','474.T0.20-12-j20-1.2.R','410.T0.20-12-j20-1.2.R','434.T0.20-12-j20-1.2.R','334.T0.20-12-j20-1.2.R','360.T0.20-12-j20-1.2.R','249.T0.20-12-j20-1.2.R','336.T0.20-12-j20-1.2.R','380.T0.20-12-j20-1.2.R','348.T0.20-12-j20-1.2.R','386.T0.20-12-j20-1.2.R','468.T0.20-12-j20-1.2.R','314.T0.20-12-j20-1.2.R','256.T0.20-12-j20-1.2.R','294.T0.20-12-j20-1.2.R','340.T0.20-12-j20-1.2.R','400.T0.20-12-j20-1.2.R','338.T0.20-12-j20-1.2.R','394.T0.20-12-j20-1.2.R','342.T0.20-12-j20-1.2.R','460.T0.20-12-j20-1.2.R','282.T0.20-12-j20-1.2.R','320.T0.20-12-j20-1.2.R','436.T0.20-12-j20-1.2.R','422.T0.20-12-j20-1.2.R','250.T0.20-12-j20-1.1.R','464.T0.20-12-j20-1.2.R','274.T0.20-12-j20-1.2.R','326.T0.20-12-j20-1.2.R','260.T0.20-12-j20-1.2.R','404.T0.20-12-j20-1.2.R','472.T0.20-12-j20-1.2.R','438.T0.20-12-j20-1.2.R','390.T0.20-12-j20-1.2.R','304.T0.20-12-j20-1.2.R','328.T0.20-12-j20-1.2.R','456.T0.20-12-j20-1.2.R','253.T0.20-12-j20-1.2.R','266.T0.20-12-j20-1.2.R','372.T0.20-12-j20-1.2.R','382.T0.20-12-j20-1.2.R','432.T0.20-12-j20-1.2.R','278.T0.20-12-j20-1.2.R','310.T0.20-12-j20-1.2.R','444.T0.20-12-j20-1.2.R','350.T0.20-12-j20-1.2.R','324.T0.20-12-j20-1.2.R','384.T0.20-12-j20-1.2.R','388.T0.20-12-j20-1.2.R','378.T0.20-12-j20-1.2.R','286.T0.20-12-j20-1.2.R','462.T0.20-12-j20-1.2.R','408.T0.20-12-j20-1.2.R','262.T0.20-12-j20-1.2.R','354.T0.20-12-j20-1.2.R','280.T0.20-12-j20-1.2.R','430.T0.20-12-j20-1.2.R','292.T0.20-12-j20-1.2.R','316.T0.20-12-j20-1.2.R','296.T0.20-12-j20-1.2.R','358.T0.20-12-j20-1.2.R','376.T0.20-12-j20-1.2.R','446.T0.20-12-j20-1.2.R','398.T0.20-12-j20-1.2.R','352.T0.20-12-j20-1.2.R','416.T0.20-12-j20-1.2.R','308.T0.20-12-j20-1.2.R','364.T0.20-12-j20-1.2.R','322.T0.20-12-j20-1.2.R','420.T0.20-12-j20-1.2.R')"
				+ "AND st.stop_id = 'de:08111:297:0:2'";
		
		
//		final String statementAsString = 
//				"SELECT t.trip_id "
//				+ "FROM routes AS r, trips AS t, calendar AS c "
//				+ "WHERE r.route_id = '20-12-j20-1' "
//				+ "AND r.route_id = t.route_id "
//				+ "AND t.trip_headsign = 'Neckargröningen Remseck' "
//				+ "AND t.service_id = c.service_id "
//				+ "AND c.monday = '1'";
////				+ "AND c.sunday = '1'";
				
		LOG.info(statementAsString);
		
		Statement statement = CCJSqlParserUtil.parse(statementAsString);
		Select selectStatement = (Select) statement;
		
		LOG.info("");
		LOG.info("TABLES: ");
		
		Map<String, Table> tables = null;
		
		DefaultTableVisitor defaultTableVisitor = new DefaultTableVisitor();
		selectStatement.accept(defaultTableVisitor);
		
		tables = defaultTableVisitor.getTables();
		
		LOG.info(tables);
		
		DefaultStatementExecutor defaultStatementExecutor = new DefaultStatementExecutor();
		defaultStatementExecutor.setDefaultTableVisitor(defaultTableVisitor);
		
		if (tables.containsKey("agency")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/agency.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("agency"), importFromFolder);
		}
		
		if (tables.containsKey("calendar")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/calendar.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("calendar"), importFromFolder);
		}
		
		if (tables.containsKey("calendar_dates")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/calendar_dates.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("calendar_dates"), importFromFolder);
		}
		
		if (tables.containsKey("feed_info")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/feed_info.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("feed_info"), importFromFolder);
		}
		
		if (tables.containsKey("routes")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/routes.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("routes"), importFromFolder);
		}
		
		if (tables.containsKey("shapes")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/shapes.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("shapes"), importFromFolder);
		}
		
		if (tables.containsKey("stop_times")) {
	//		defaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/stop_times.txt");
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/stop_times.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("stop_times"), importFromFolder);
		}
		
		if (tables.containsKey("stops")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/stops.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("stops"), importFromFolder);
		}
		
		if (tables.containsKey("transfers")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/transfers.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("transfers"), importFromFolder);
		}
		
		if (tables.containsKey("trips")) {
			Set<CSVRecord> importFromFolder = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/trips.txt");
			defaultStatementExecutor.getTableRecord().put(tables.get("trips"), importFromFolder);
		}
		
//		if (tables.containsKey("a")) {
//	//		defaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/trips.txt");
//			Set<CSVRecord> records = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/a.txt");
//			defaultStatementExecutor.getTableRecord().put(tables.get("a"), records);
//		}
//		if (tables.containsKey("b")) {
//	//		defaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/trips.txt");
//			Set<CSVRecord> records = DefaultCSVImporter.importFromFolder("/Users/bischowg/Downloads/vvs_gtfs/b.txt");
//			defaultStatementExecutor.getTableRecord().put(tables.get("b"), records);
//		}
		
		LOG.info("");
		LOG.info("SELECT CLAUSE: COLUMNS AND THEIR ALIASES: ");
		Map<String, Expression> map = new HashMap<>();
		
		for (SelectItem selectItem : ((PlainSelect) selectStatement.getSelectBody()).getSelectItems()) {
			selectItem.accept(new SelectItemVisitorAdapter() {
				
				@Override
				public void visit(SelectExpressionItem item) {
					String alias = item.getAlias() == null ? item.getExpression().toString() : item.getAlias().getName();
					map.put(alias, item.getExpression());
				}
			});
		}

		// if the map has no entries, the user selected over asterisk *
		LOG.info("map " + map);
		
		defaultStatementExecutor.setStartTable((Table)((PlainSelect) selectStatement.getSelectBody()).getFromItem());
			
		
		LOG.info("");
		LOG.info("FROM CLAUSE: COLUMNS AND VALUES ");
		// contained in the from item and the joins of ((PlainSelect) statement.getSelectBody())
		
		LOG.info("");
		LOG.info("WHERE CLAUSE: COLUMNS AND VALUES ");
		DefaultExpressionVisitor defaultExpressionVisitor = new DefaultExpressionVisitor();
		SelectBody selectBody = selectStatement.getSelectBody();
		PlainSelect plainSelectBody = (PlainSelect) selectBody;
		Expression whereExpression = plainSelectBody.getWhere();
		if (whereExpression != null) {
			whereExpression.accept(defaultExpressionVisitor);
		}
		defaultStatementExecutor.getConstraints().addAll(defaultExpressionVisitor.getConstraints());
		
		LOG.info("");
		LOG.info("ORDER BY: ");
		PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
		List<OrderByElement> orderByElements = plainSelect.getOrderByElements();
		LOG.info(orderByElements);
		
		
		List<DefaultResultRecord> executeResult = defaultStatementExecutor.execute();
		
		for (DefaultResultRecord defaultResultRecord : executeResult) {
			System.out.print(defaultResultRecord.select(map, defaultTableVisitor));
		}
		
		LOG.info("End");
	}

}
