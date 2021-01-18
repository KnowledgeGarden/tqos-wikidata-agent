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
public class FetchTest extends TestRoot {
//[{"id":"Q31","label":"Belgium"}, 
//{"id":"Q8","label":"happiness"}, 
//{"id":"Q23","label":"George Washington"}, 
//{"id":"Q24","label":"Jack Bauer"}, {"id":"Q42","label":"Douglas Adams"}, {"id":"Q1868","label":"Paul Otlet"}, {"id":"Q2013","label":"Wikidata"}, {"id":"Q45","label":"Portugal"}, {"id":"Q51","label":"Antarctica"}]

	/**
	 * 
	 */
	public FetchTest() {
		super();
		
		IResult r = model.getJSON("Q23");
		System.out.println("A "+r.getErrorString());
		String json = (String)r.getResultObject();
		System.out.println("B "+(json == null));
		if (json != null)
			System.out.println(json.substring(0, 100));
		
		environment.shutDown();
		System.exit(0);
	}

}
