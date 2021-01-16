package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.util.List;

import tec.diseno.communities.helper.XMLHelper;

public class DocumentXML implements DocumentStrategy{

	@Override
	public ByteArrayInputStream generateDocument(List<Contribution> list) {
		return XMLHelper.tutorialsToXML(list);
	}

}
