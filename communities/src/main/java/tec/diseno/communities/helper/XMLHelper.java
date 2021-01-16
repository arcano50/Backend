package tec.diseno.communities.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tec.diseno.communities.model.Contribution;

public class XMLHelper {
	public static ByteArrayInputStream tutorialsToXML(List<Contribution> contributions) {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
	    final ObjectMapper mapper = new ObjectMapper();

	    try {
			mapper.writeValue(out, contributions);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    final byte[] data = out.toByteArray();
		return new ByteArrayInputStream(data);
	}
}
