package com.jachil.onlinewhiteboard.config;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class CouchDbConfig {

    @Bean
    public CouchDbConnector couchDbConnector() throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder()
                .url("http://127.0.0.1:5984")
                .username("admin")
                .password("password")
                // Cookie E6B22B588220EF6B70F14DC840284EC7
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        CouchDbConnector dbConnector = new StdCouchDbConnector("whiteboardDB", dbInstance);
        dbConnector.createDatabaseIfNotExists();
        return dbConnector;
    }
}
