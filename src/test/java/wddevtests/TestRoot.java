/**
 * 
 */
package wddevtests;

import org.topicquests.ks.os.wikidata.WikidataEnvironment;
import org.topicquests.ks.os.wikidata.api.IWikidataModel;
/**
 * @author jackpark
 *
 */
public class TestRoot {
	protected WikidataEnvironment environment;
	protected IWikidataModel model;

	/**
	 * 
	 */
	public TestRoot() {
		environment = new WikidataEnvironment();
		model = environment.getModel();
	}

}
