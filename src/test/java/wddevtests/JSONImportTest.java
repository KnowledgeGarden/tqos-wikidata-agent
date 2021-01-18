/**
 * 
 */
package wddevtests;

import org.topicquests.ks.os.wikidata.jsondata.JSONDataImporter;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class JSONImportTest extends TestRoot {
	private JSONDataImporter jsonImporter;
	private final String PATH = "/home/jackpark/Downloads/latest-all.json.gz";

	/**
	 * 
	 */
	public JSONImportTest() {
		super();
		jsonImporter = environment.getJSONImporter();
		
		IResult r = jsonImporter.importJSONData(PATH);
		System.out.println("A "+r.getErrorString());
		System.out.println("B "+r.getResultObject());
		
		environment.shutDown();
		System.exit(0);

	}
//A 
//B [{"id":"Q31","label":"Belgium"}, {"id":"Q8","label":"happiness"}, {"id":"Q23","label":"George Washington"}, {"id":"Q24","label":"Jack Bauer"}, {"id":"Q42","label":"Douglas Adams"}, {"id":"Q1868","label":"Paul Otlet"}, {"id":"Q2013","label":"Wikidata"}, {"id":"Q45","label":"Portugal"}, {"id":"Q51","label":"Antarctica"}]

}
