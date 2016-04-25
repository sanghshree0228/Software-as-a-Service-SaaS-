package com.sanghshree.restful_service.carematcher.Connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection_Pool {
	
	public Connection sendConnection(){
		Connection con = null;
	    try {
	    		
	    		Class.forName("org.postgresql.Driver");
	    		con = DriverManager.getConnection("jdbc:postgresql://ec2-23-21-167-249.compute-1.amazonaws.com:5432/d3u10ghv9s54cl?sslmode=require",
	    				"nbrmpptirhpmww", "YJS0G7uqig8LE8em7m2mkd5mZ6");
	    		System.out.printf("the connection is",con);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		System.err.println(e.getClass().getName()+": "+e.getMessage());
	    		System.exit(0);
	    	}
	  return con;

	}
	

}

