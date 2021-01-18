
package tec.diseno.communities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import tec.diseno.communities.helper.EmailHelper;
import tec.diseno.communities.model.AbstractAdministrativeLevelServices;

@SpringBootApplication
@ComponentScan({"tec.diseno.communities.controller"})
@ComponentScan({"tec.diseno.communities.model"})
public class App {
	
	@Autowired
	AbstractAdministrativeLevelServices services;
	
    public static void main( String[] args )
    {
    	ApplicationContext context = SpringApplication.run(App.class, args);
    	AbstractAdministrativeLevelServices services =
    			context.getBean(AbstractAdministrativeLevelServices.class);
    	
    	/*
    	try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/mfyrmgza";
        String username = "mfyrmgza";
        String password = "5MK12PxXIKIOKoEQLwG-u68rF_MiXfHt";

        try {
            Connection db = DriverManager.getConnection(url, username, password);
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM \"Coordination\"");
            while (rs.next()) {
                System.out.print(rs.getString("name"));
            }
            rs.close();
            st.close();
            }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        */
    }
}
