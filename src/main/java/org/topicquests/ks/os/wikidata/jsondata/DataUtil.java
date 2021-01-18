/**
 * 
 */
package org.topicquests.ks.os.wikidata.jsondata;

import org.topicquests.ks.os.wikidata.WikidataEnvironment;

import net.minidev.json.JSONObject;

/**
 * @author jackpark
 *
 */
public class DataUtil {
	private WikidataEnvironment environment;

	/**
	 * 
	 */
	public DataUtil(WikidataEnvironment env) {
		environment = env;
	}

	public String getEnglishLabel(JSONObject wikidata) {
		JSONObject labels = (JSONObject) wikidata.get("labels");
		labels = (JSONObject) labels.get("en");
		return labels.getAsString("value");
	}
	
	public String getId(JSONObject wikidata) {
		return wikidata.getAsString("id");
	}
}
