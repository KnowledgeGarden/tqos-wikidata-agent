/**
 * 
 */
package org.topicquests.ks.os.wikidata.api;

import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public interface IWikidataModel {

	IResult putJSON(String id, String label, String jsonData);
	
	IResult getJSON(String id);
	
	IResult findByLabel(String label);
	
	IResult findByFuzzyLabel(String label);
	
	IResult putOrUpdate(String id, String label, String jsonData);
	
}
