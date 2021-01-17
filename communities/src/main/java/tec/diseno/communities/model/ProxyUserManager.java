package tec.diseno.communities.model;

import java.io.ByteArrayInputStream;

public class ProxyUserManager implements UserManager{
	private String userType;
	private UserManager userManager;

	public ProxyUserManager(String userType, UserManager userManager) {
		this.userType = userType;
		this.userManager = userManager;
	}

	@Override
	public ByteArrayInputStream getContribution(String type) {
		switch(this.userType) {
		case "MEMBER":
			return null;
		case "BOSS":
			return null;
		case "ADMIN":
			return null;
		default:
			return null;
		}
	}
	
	
}
