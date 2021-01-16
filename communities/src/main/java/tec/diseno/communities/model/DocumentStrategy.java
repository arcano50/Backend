package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface DocumentStrategy {
	public ByteArrayInputStream generateDocument(List<Contribution> list);
}
