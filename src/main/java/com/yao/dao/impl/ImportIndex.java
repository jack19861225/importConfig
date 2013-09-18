package com.yao.dao.impl;

import com.yao.entity.AllConfig;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ImportIndex {

    public void importIndex(AllConfig indexObj, String[] rs)
            throws Exception {

        bulkRequest = client.prepareBulk();


        bulkRequest.add(client.prepareIndex(indexObj.getIndex_name(),
                indexObj.getIndex_type()
                // rs.getString(indexConfig.getTableId())// this is for es index id
                // ,remove
                , rs[0]
        ).setSource(getJsonObject(indexObj, rs)));


        bulkResponse = bulkRequest.execute().actionGet();

        if (bulkResponse != null && bulkResponse.hasFailures()) {
            String s = bulkResponse.buildFailureMessage();
            System.out.println(s);
        }

        System.gc();
    }

    public void importIndex(AllConfig indexObj, ResultSet rs)
            throws SQLException, IOException {

        bulkRequest = client.prepareBulk();

        while (rs.next()) {

            bulkRequest.add(client.prepareIndex(indexObj.getIndex_name(),
                    indexObj.getIndex_type()
                    // rs.getString(indexConfig.getTableId())// this is for es index id
                    // ,remove
                    , rs.getString(indexObj.getIdentify())
            ).setSource(getJsonObject(indexObj, rs)));
        }

        bulkResponse = bulkRequest.execute().actionGet();

        if (bulkResponse != null && bulkResponse.hasFailures()) {
            String s = bulkResponse.buildFailureMessage();
            System.out.println(s);
        }

        System.gc();
    }

    public void initClusterClient(AllConfig cluster) {
        settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", cluster.getCluster_name()).build();
        client = new TransportClient(settings);
        ista = new InetSocketTransportAddress(cluster.getCluster_host(),
                Integer.valueOf(cluster.getCluster_port()));
        client = client.addTransportAddress(ista);
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }

    private XContentBuilder getJsonObject(AllConfig indexObj, ResultSet results)
            throws IOException, SQLException {
        final XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();

        jsonBuilder.startObject();

        Map<String, String> map = indexObj.getMap();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        String temp = null;
        while (it.hasNext()) {
            temp = it.next();
            jsonBuilder.field(map.get(temp), results.getString(temp));

        }

        jsonBuilder.endObject();
        return jsonBuilder;
    }

    private XContentBuilder getJsonObject(AllConfig indexObj, String[] results)
            throws IOException, SQLException {
        final XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();

        jsonBuilder.startObject();

        Map<String, String> map = indexObj.getMap();
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        String temp = null;
        int number = 0;
        while (it.hasNext()) {
            temp = it.next();
            jsonBuilder.field(map.get(temp), results[number++]);

        }

        jsonBuilder.endObject();
        return jsonBuilder;
    }

    private Settings settings;
    private TransportClient client;
    private InetSocketTransportAddress ista;
    private BulkRequestBuilder bulkRequest = null;
    private BulkResponse bulkResponse = null;

}
