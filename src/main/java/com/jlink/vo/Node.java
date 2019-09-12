package com.jlink.vo;

import lombok.Data;

import java.util.List;

@Data
public class Node {
    private String id;
    private String text;
    private List<Node> children;
}
