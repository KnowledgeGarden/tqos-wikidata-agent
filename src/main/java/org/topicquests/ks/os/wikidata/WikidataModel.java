/**
 * 
 */
package org.topicquests.ks.os.wikidata;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.topicquests.ks.os.wikidata.api.IWikidataModel;
import org.topicquests.pg.PostgresConnectionFactory;
import org.topicquests.pg.api.IPostgresConnection;
import org.topicquests.support.ResultPojo;
import org.topicquests.support.api.IResult;

/**
 * @author jackpark
 *
 */
public class WikidataModel implements IWikidataModel {
	private WikidataEnvironment environment;
	private PostgresConnectionFactory factory;

	/**
	 * @param env
	 * Qparam f
	 */
	public WikidataModel(WikidataEnvironment env, PostgresConnectionFactory f) {
		environment = env;
		factory = f;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.ks.os.wikidata.api.IWikidataModel#putJSON(java.lang.String)
	 */
	@Override
	public IResult putJSON(String id, String label, String jsonData) {
		IResult result = new ResultPojo();
		String sql = "INSERT INTO tq_wikidata.items (id, label, data) VALUES (?, ?, ?)";
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = factory.getConnection();
	       	conn.setProxyRole(r);
	    	Object [] vals = new Object[3];
	    	vals[0] = id;
	    	vals[1] = label;
	    	vals[2] = jsonData;
	    	conn.executeSQL(sql, r, vals);
	    	if (r.hasError())
	    		result.addErrorString(r.getErrorString());
	       	
	    } catch (SQLException e) {
        	environment.logError(e.getMessage(), e);
            result.addErrorString(e.getMessage());
        } finally {
        	conn.closeConnection(r);
        }
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.ks.os.wikidata.api.IWikidataModel#getJSON(java.lang.String)
	 */
	@Override
	public IResult getJSON(String id) {
		IResult result = new ResultPojo();
		String sql = "SELECT data FROM tq_wikidata.items WHERE id=?";
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = factory.getConnection();
	       	conn.setProxyRole(r);
	    	Object [] vals = new Object[1];
	    	vals[0] = id;
        	conn.executeSelect(sql, r, vals);
        	ResultSet rs = (ResultSet)r.getResultObject();
        	environment.logDebug("FetchId "+rs);
        	if (rs != null) {
        		if (rs.next()) {
                	environment.logDebug("FetchId-1 "+rs.getString("data"));
                	result.setResultObject(rs.getString("data"));
        		}
        	}
	    	if (r.hasError())
	    		result.addErrorString(r.getErrorString());
	       	
	    } catch (SQLException e) {
        	environment.logError(e.getMessage(), e);
            result.addErrorString(e.getMessage());
        } finally {
        	conn.closeConnection(r);
        }
		return result;
	}

	/* (non-Javadoc)
	 * @see org.topicquests.ks.os.wikidata.api.IWikidataModel#findByLabel(java.lang.String)
	 */
	@Override
	public IResult findByLabel(String label) {
		IResult result = new ResultPojo();
		String sql = "SELECT data FROM tq_wikidata.items WHERE label=?";
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = factory.getConnection();
	       	conn.setProxyRole(r);
	    	Object [] vals = new Object[1];
	    	vals[0] = label;
        	conn.executeSelect(sql, r, vals);
        	ResultSet rs = (ResultSet)r.getResultObject();
        	if (rs != null) {
        		if (rs.next())
        			result.setResultObject(rs.getString("data"));
        	}
	    	if (r.hasError())
	    		result.addErrorString(r.getErrorString());
	       	
	    } catch (SQLException e) {
        	environment.logError(e.getMessage(), e);
            result.addErrorString(e.getMessage());
        } finally {
        	conn.closeConnection(r);
        }
		return result;
	}

	@Override
	public IResult findByFuzzyLabel(String label) {
		IResult result = new ResultPojo();
		String sql = "SELECT data FROM tq_wikidata.items WHERE label=?"; //TODO "like"
	    IPostgresConnection conn = null;
	    IResult r = new ResultPojo();
	    try {
	    	conn = factory.getConnection();
	       	conn.setProxyRole(r);
	    	Object [] vals = new Object[1];
	    	vals[0] = label;
        	conn.executeSelect(sql, r, vals);
        	ResultSet rs = (ResultSet)r.getResultObject();
        	if (rs != null) {
        		if (rs.next())
        			result.setResultObject(rs.getString("data"));
        	}
	    	if (r.hasError())
	    		result.addErrorString(r.getErrorString());
	       	
	    } catch (SQLException e) {
        	environment.logError(e.getMessage(), e);
            result.addErrorString(e.getMessage());
        } finally {
        	conn.closeConnection(r);
        }
		return result;
	}

	@Override
	public IResult putOrUpdate(String id, String label, String jsonData) {
		// TODO Auto-generated method stub
		return null;
	}

}
