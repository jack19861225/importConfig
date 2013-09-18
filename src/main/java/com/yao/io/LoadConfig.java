package com.yao.io;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.yao.entity.AllConfig;


public class LoadConfig {
	
	public static AllConfig load() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;
		db=dbf.newDocumentBuilder();
		doc=db.parse(CONFIGFILE);
		
		String db_url=getNodeValueByTagName("database-url");
		String db_username=getNodeValueByTagName("database-username");
		String db_password=getNodeValueByTagName("database-password");
		String db_name=getNodeValueByTagName("database-name");
		String db_table=getNodeValueByTagName("database-table");
		String cluster_name=getNodeValueByTagName("cluster-name");
		String cluster_host=getNodeValueByTagName("cluster-host");
		String cluster_port=getNodeValueByTagName("cluster-port");
		String batch_id=getNodeValueByTagName("batchId");
		String identify=getNodeValueByTagName("identify");
		String index_name=getNodeValueByTagName("index-name");
		String index_type=getNodeValueByTagName("index-type");
		String batchSize=getNodeValueByTagName("batch-size");
		
		
		
		Map<String, String> map=null;
		try {
			map = getMap(index_name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取 map 错误");
		}
		AllConfig config=new AllConfig(db_url, db_username, db_password, db_name, db_table, cluster_name, cluster_host, cluster_port, batch_id, identify, index_name, index_type, batchSize, map);
		return config;
	};
	

	
	
	private static Map<String, String> getMap(String indexName) throws Exception {
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;

		db=dbf.newDocumentBuilder();
		doc=db.parse(new File(MAPPINGDIR,indexName+".xml"));
		
		NodeList nodeList=doc.getElementsByTagName("mapping");
		Map<String, String> map=new HashMap<String, String>();
		
		NamedNodeMap namedNodeMap=null;
		for (int i = 0; i < nodeList.getLength(); i++) {
			namedNodeMap=nodeList.item(i).getAttributes();
			map.put(namedNodeMap.item(0).getNodeValue(), namedNodeMap.item(1).getNodeValue());
		}
		
		
		return map;
	}
	
	
	


	private static String getNodeValueByTagName(String tagName){
		return doc.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	private static final String MAPPINGDIR="mapping";
	private static final String CONFIGFILE="importConfig.xml";
	private static Document doc;
	
	
	
	
	

}
