package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;

public interface ContributionBuilder {
	public void addContribution(Contribution contribution);
	public ByteArrayInputStream getContributions(String type);
}
