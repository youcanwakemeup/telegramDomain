package com.example.domainTelegram.domainTelegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UrlData {

    @JsonProperty("domainname")
    private String domainName;

    private int hotness;

    private int price;

    private int links;

    private int visitors;

    private int old;

    @JsonProperty("delete_date")
    private LocalDate deleteDate;

    private boolean block;
}
