package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthorizated implements UserManager{
	
	@Autowired
	AbstractAdministrativeLevelServices services;
	
	@Override
	public ByteArrayInputStream getContribution(String type) {
		return services.getContributions(type);
	}

}
