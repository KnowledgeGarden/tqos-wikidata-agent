/**
 * 
 */
package org.topicquests.ks.os.wikidata.jsondata;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

import org.topicquests.ks.os.wikidata.WikidataEnvironment;
import org.topicquests.ks.os.wikidata.api.IWikidataModel;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.util.zip.GZIPInputStream;
/**
 * @author jackpark
 *
 */
public class JSONDataImporter {
	private WikidataEnvironment environment;
	private IWikidataModel model;
	private DataUtil util;

	/**
	 * @param env
	 * @param m
	 */
	public JSONDataImporter(WikidataEnvironment env, IWikidataModel m) {
		environment = env;
		model = m;
		util = new DataUtil(environment);
	}
	
	public IResult importJSONData(String filePath) {
		IResult result = new ResultPojo();
		List<JSONObject> items = new ArrayList<JSONObject>();
		result.setResultObject(items);
		JSONObject ix;
		System.out.println("IMP "+filePath);
		File f = new File(filePath);
		System.out.println("IMP.1 "+f.exists());
		try {
	        GZIPInputStream in = new GZIPInputStream(new FileInputStream(f));
	        InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
	        BufferedReader bfr = new BufferedReader(isr);
	        JSONParser p = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
	        JSONObject jo;
	        //JSONArray ja = (JSONArray)p.parse(bfr);
	        //System.out.println(ja.size());
	        long counter = 0;
	        String line = bfr.readLine();
	        IResult r;
	        String id, label;
	        while (line != null) {
	        	line = cleanLine(line);
	        	if (line.length() > 10 ) {
	        		try {
	        			jo = (JSONObject)p.parse(line);
	        			environment.logDebug("J\n"+jo);
	        			id = util.getId(jo);
	        			label =  util.getEnglishLabel(jo);
	        			//ix = new JSONObject();
	        			//ix.put("id", id);
	        			//ix.put("label", label);
	        			//items.add(ix);
	        			r = model.putJSON(id, label, jo.toJSONString());
	        			System.out.println(id+" "+counter+" "+label);
	        			counter++;
	        			if (r.hasError()) {
	        				result.addErrorString(r.getErrorString());
	        				System.out.println(r.getErrorString());
	        			}
	        		} catch (Exception pe) {
	        			environment.logError(pe.getMessage(), null);
	        		}
	        	}
	        	line = bfr.readLine();
	        }
	        
        
	        bfr.close();
		} catch (Exception e) {
			e.printStackTrace();
			environment.logError(e.getMessage(), e);
			result.addErrorString(e.getMessage());
		}
		return result;
	}

	String cleanLine(String line) {
		String result = line.trim();
		if (line.endsWith(","))
			line = line.substring(0, (line.length()-1));
		return result;
	}
}
