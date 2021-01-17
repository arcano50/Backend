package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ContributionBuilder {
	public void addContribution(Contribution contribution);
	public ByteArrayInputStream getContributions(String type);
        public List<ContributionReport> getContributionReport();
}
