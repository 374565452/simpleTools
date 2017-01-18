package com.simple.tools.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import com.simple.tools.solr.SolrService;

public class TestSolr {
	String urlString = "http://localhost:8081/solr/core1";
	@Test
	public void testSolr() {
		try {
			//String urlString = "http://localhost:8081/solr/core1";
			SolrClient solr = new HttpSolrClient.Builder(urlString).build();
			
			for(int i =10;i<30;i++){
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", "552199="+i);
				document.addField("item_title", "Gouda cheese wheel=="+i);
				UpdateResponse response = solr.add(document);
			}
			//document.addField("price", "49.99");
			
			solr.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuery(){
		SolrService ss=new SolrService();
		SolrDocumentList query = ss.query("Gouda");
		System.out.println("共查询到记录：" + query.getNumFound());
		for (SolrDocument solrDocument : query) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			//System.out.println(solrDocument.get("item_price"));
			//System.out.println(solrDocument.get("item_image"));
			
		}
	}
}
