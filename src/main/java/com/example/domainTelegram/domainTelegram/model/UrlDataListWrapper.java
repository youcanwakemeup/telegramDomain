package com.example.domainTelegram.domainTelegram.model;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class UrlDataListWrapper {
    private List<UrlData> urlDataList;

}
