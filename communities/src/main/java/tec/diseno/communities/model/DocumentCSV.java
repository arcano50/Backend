package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.util.List;

import tec.diseno.communities.helper.CSVHelper;

public class DocumentCSV implements DocumentStrategy{

	@Override
	public ByteArrayInputStream generateDocument(List<Contribution> list) {
		return CSVHelper.tutorialsToCSV(list);
	}

}
