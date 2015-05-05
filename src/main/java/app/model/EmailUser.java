package app.model;

import org.apache.solr.client.solrj.beans.Field;

public class EmailUser {

	@Field
	String id;
	@Field("email_name")
	String name;
	@Field("email_email")
	String email;
	
	public EmailUser(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + "]";
	}
	

}
