package com.example.domainTelegram.domainTelegram.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "daily_domains")
public class Domain {

    @Id
    @Column(name = "domain_name")
    private String domainName;

    @Column(name = "hotness")
    private int hotness;

    @Column(name = "price")
    private int price;

    @Column(name = "links")
    private int links;

    @Column(name = "visitors")
    private int visitors;

    @Column(name = "old")
    private int old;

    @Column(name = "delete_date")
    private LocalDate deleteDate;

    @Column(name = "block")
    private boolean isBlocked;
}
