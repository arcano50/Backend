package tec.diseno.communities.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import tec.diseno.communities.model.Contribution;

public class CSVHelper {
	public static ByteArrayInputStream tutorialsToCSV(List<Contribution> contributions) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	    	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	    	
	      for (Contribution contribution : contributions) {
	        List<String> data = Arrays.asList(
	        		dateFormat.format(contribution.getDate()),
	        		Integer.toString(contribution.getMemeber().getId()),
	        		contribution.getContent(),
	        		contribution.getMemeber().getName(),
	        		contribution.getSubject(),
	        		contribution.getType());

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }
}
