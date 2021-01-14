package tec.diseno.communities.model;

import java.util.ArrayList;

public class EmailAlertListener implements EventListener {
	
	private String email;

    public EmailAlertListener(String email) {
        this.email = email;
    }

	@Override
	public void update(String eventType) {
		System.out.println("A email has seeede to: " + email);
	}

}
