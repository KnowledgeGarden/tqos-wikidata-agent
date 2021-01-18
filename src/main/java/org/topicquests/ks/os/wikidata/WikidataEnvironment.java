/**
 * 
 */
package org.topicquests.ks.os.wikidata;

import org.topicquests.ks.os.wikidata.api.IWikidataModel;
import org.topicquests.ks.os.wikidata.jsondata.JSONDataImporter;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.support.RootEnvironment;

/**
 * @author jackpark
 *
 */
public class WikidataEnvironment extends RootEnvironment {
	private JSONDataImporter jsonImporter;
	private IWikidataModel model;
	private PostgresConnectionFactory factory;

	/**
	 * 
	 */
	public WikidataEnvironment() {
		super("wikidata-props.xml", "logger.properties");
		// build the database
		String dbName = getStringProperty("WDDatabaseName");
		String schemaName = getStringProperty("WDDatabaseSchema");
		factory = new PostgresConnectionFactory(dbName, schemaName);

		model = new WikidataModel(this, factory);
		jsonImporter = new JSONDataImporter(this, model);
	}
	
	public JSONDataImporter getJSONImporter() {
		return jsonImporter;
	}
	
	public IWikidataModel getModel() {
		return model;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.support.RootEnvironment#shutDown()
	 */
	@Override
	public void shutDown() {
		// TODO Auto-generated method stub

	}

}
