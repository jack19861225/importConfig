package com.yao.entity;

import java.util.Map;

public class AllConfig {
	private String db_url;
	private String db_username;
	private String db_password;
	private String db_name;
	private String db_table;
	private String cluster_name;
	private String cluster_host;
	private String cluster_port;
	private String batch_id;
	private String identify;
	private String index_name;
	private String index_type;
	private String batchSize;
	public AllConfig(String db_url, String db_username, String db_password,
			String db_name, String db_table, String cluster_name,
			String cluster_host, String cluster_port, String batch_id,
			String identify, String index_name, String index_type,
			String batchSize, Map<String, String> map) {
		super();
		this.db_url = db_url;
		this.db_username = db_username;
		this.db_password = db_password;
		this.db_name = db_name;
		this.db_table = db_table;
		this.cluster_name = cluster_name;
		this.cluster_host = cluster_host;
		this.cluster_port = cluster_port;
		this.batch_id = batch_id;
		this.identify = identify;
		this.index_name = index_name;
		this.index_type = index_type;
		this.batchSize = batchSize;
		this.map = map;
	}

	public String getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(String batchSize) {
		this.batchSize = batchSize;
	}
	private Map<String, String> map;
	
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	
	

	public String getIndex_name() {
		return index_name;
	}

	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}

	public String getIndex_type() {
		return index_type;
	}

	@Override
	public String toString() {
		return "AllConfig [db_url=" + db_url + ", db_username=" + db_username
				+ ", db_password=" + db_password + ", db_name=" + db_name
				+ ", db_table=" + db_table + ", cluster_name=" + cluster_name
				+ ", cluster_host=" + cluster_host + ", cluster_port="
				+ cluster_port + ", batch_id=" + batch_id + ", identify="
				+ identify + ", index_name=" + index_name + ", index_type="
				+ index_type + ", batchSize=" + batchSize + ", map=" + map
				+ "]";
	}

	public void setIndex_type(String index_type) {
		this.index_type = index_type;
	}

	public AllConfig(String db_url, String db_username, String db_password,
			String db_name, String db_table, String cluster_name,
			String cluster_host, String cluster_port, String batch_id,
			String identify, String index_name, String index_type,
			Map<String, String> map) {
		super();
		this.db_url = db_url;
		this.db_username = db_username;
		this.db_password = db_password;
		this.db_name = db_name;
		this.db_table = db_table;
		this.cluster_name = cluster_name;
		this.cluster_host = cluster_host;
		this.cluster_port = cluster_port;
		this.batch_id = batch_id;
		this.identify = identify;
		this.index_name = index_name;
		this.index_type = index_type;
		this.map = map;
	}

	public AllConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDb_url() {
		return db_url;
	}
	public void setDb_url(String db_url) {
		this.db_url = db_url;
	}
	public String getDb_username() {
		return db_username;
	}
	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}
	public String getDb_password() {
		return db_password;
	}
	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
	public String getDb_table() {
		return db_table;
	}
	public void setDb_table(String db_table) {
		this.db_table = db_table;
	}
	public String getCluster_name() {
		return cluster_name;
	}
	public void setCluster_name(String cluster_name) {
		this.cluster_name = cluster_name;
	}
	public String getCluster_host() {
		return cluster_host;
	}
	public void setCluster_host(String cluster_host) {
		this.cluster_host = cluster_host;
	}
	public String getCluster_port() {
		return cluster_port;
	}
	public void setCluster_port(String cluster_port) {
		this.cluster_port = cluster_port;
	}
	public String getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}


	
}
