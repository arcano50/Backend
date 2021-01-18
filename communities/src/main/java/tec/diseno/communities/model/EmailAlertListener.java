package tec.diseno.communities.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tec.diseno.communities.helper.EmailHelper;

@Component
public class EmailAlertListener implements EventListener {
	
	private String emailName;
	
	@Autowired
    private EmailHelper emailHelper;
	

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

	@Override
	public void update(String eventType) {
		System.out.println("emailName: " + emailName);
		emailHelper.sendEmail(emailName);
	}

}
