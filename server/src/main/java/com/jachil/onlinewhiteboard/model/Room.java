package com.jachil.onlinewhiteboard.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ektorp.support.CouchDbDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Room extends CouchDbDocument {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("_rev")
    private String revision;

    private String name;
    private String description;
    private String address;

    private List<String> participants;
    private List<Drawing> drawings;
}
