package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.solr.client.solrj.SolrServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.model.EmailMessage;
import app.solr.SolrConnection;

import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

public class PSTReader {
	
	private static final Logger LOG = LoggerFactory.getLogger(PSTReader.class);
	private static final String IPM_NOTE = "IPM.Note";
	
	private SolrConnection connection;
	
	public static void main(String[] args) {
		new PSTReader("src/resources/LargePST.pst");
	}

	public PSTReader(String filename) {
		try {
			PSTFile pstFile = new PSTFile(filename);			
			connection = new SolrConnection();
			processFolder(pstFile.getRootFolder());
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	public void processFolder(PSTFolder folder) throws Exception {
		
		// go through the folders...
		if (folder.hasSubfolders()) {
			Vector<PSTFolder> childFolders = folder.getSubFolders();
			for (PSTFolder childFolder : childFolders) {
				processFolder(childFolder);
			}
		}

		// and now the emails for this folder
		if (folder.getContentCount() > 0) {
			processEmails(folder);
		}
	}
	
	private void processEmails(PSTFolder folder) throws Exception {
		PSTMessage email;
		SolrServer server = connection.getServer();
		List<EmailMessage> messages = new ArrayList<>();
		
		for (Integer i = 1; i <= folder.getContentCount(); i++) {
			email = (PSTMessage) folder.getNextChild();
			
			if (email.getMessageClass().equals(IPM_NOTE)) {
				String id = folder.getDisplayName().toLowerCase() + i.toString();
				EmailMessage emailMessage = new EmailMessage(id, email);
//				if (email.getSubject().toLowerCase().contains("stock reconciliation")) {
//					System.out.println(email.getMessageDeliveryTime());
//					System.out.println(emailMessage);
					messages.add(emailMessage);
					try {
						server.addBean(emailMessage);
					} catch (Exception e) {
						e.printStackTrace();
					}
//				}
			} else {
				break;
			}
		}

//		server.addBeans(messages);
		server.commit();
		
	}
}

