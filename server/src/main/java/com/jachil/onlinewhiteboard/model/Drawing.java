package com.jachil.onlinewhiteboard.model;

import lombok.Data;

import java.util.List;

@Data
public class Drawing {
    private List<Coordinates> line;
}