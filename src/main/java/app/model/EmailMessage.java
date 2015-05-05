package app.model;

import org.apache.solr.client.solrj.beans.Field;

import com.pff.PSTMessage;

public class EmailMessage {

	@Field
	String id;
	@Field
	String fromName;
	@Field
	String fromEmail;
	@Field
	String to;
	@Field
	String cc;
	@Field
	String bcc;
	@Field
	String subject;
	@Field
	String body;

	public EmailMessage() {
	}

	public EmailMessage(String id) {
		this.id = id;
	}

	public EmailMessage(String id, PSTMessage email) {
		this(id);

		fromName = email.getSenderName();
		fromEmail = email.getSenderEmailAddress();

		to = email.getDisplayTo();
		cc = email.getDisplayCC();
		bcc = email.getDisplayBCC();
		subject = email.getSubject();
		body = email.getBody();
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "EmailMessage [id=" + id + ", fromName=" + fromName
				+ ", fromEmail=" + fromEmail + ", to=" + to + ", cc=" + cc
				+ ", bcc=" + bcc + ", subject=" + subject + "]";
	}

}
