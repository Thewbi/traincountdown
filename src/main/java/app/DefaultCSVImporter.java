package app;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCSVImporter {

	@SuppressWarnings("unused")
	private static final Logger LOG = LogManager.getLogger(DefaultCSVImporter.class);

	private DefaultCSVImporter() {
		// no instances of this class
	}

	public static Set<CSVRecord> importFromFolder(final String folderAbsolutePath) throws IOException {

		BOMInputStream bomInputStream = new BOMInputStream(FileUtils.openInputStream(new File(folderAbsolutePath)));
		Reader targetReader = new InputStreamReader(bomInputStream);

		// with quote null allows quotes within quotes such as in
		// "21-10-j20-1","","10","Marienplatz - Degerloch (Zahnradbahn
		// "Zacke")","1400","FFB300","004299"
		Iterable<CSVRecord> iterable = CSVFormat.DEFAULT.withQuote(null).withEscape('\'').withQuoteMode(QuoteMode.NONE)
				.withFirstRecordAsHeader().parse(targetReader);

		// use set because List.removeAll() is so slow that it even returns in a
		// reasonable amount of time for large data sets.
		// As the stop times are a large data set a list cannot be used. Use set instead
		// which has a pretty fast removeAll()
		Set<CSVRecord> set = new HashSet<>();
		iterable.forEach(set::add);

		// you can only iterate once using an iterator so return a list for easier
		// access
		return set;
	}

}
