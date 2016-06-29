package de.unimuenster.wfm.capitol.service;

import javax.ejb.Remote;

@Remote
public interface MessageService {
	
	public void sendJSON(Object input, String urlString);

}
