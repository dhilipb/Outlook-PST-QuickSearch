package app.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.PSTReader;

public class SolrConnection {
	
	private static final Logger LOG = LoggerFactory.getLogger(PSTReader.class);
	
	private static final String SOLR_URL = "http://localhost:8983/solr/emails/";
	private final SolrServer server;
	
	public SolrConnection() {
		server = new HttpSolrServer( SOLR_URL );
		try {
			clear();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clear() throws SolrServerException, IOException {
		server.deleteByQuery("*:*");
	}
	
	public SolrServer getServer() {
		return server;
	}
	
	
}
