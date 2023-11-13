package com.example.domainTelegram.domainTelegram.service;

import com.example.domainTelegram.domainTelegram.handler.BotHandler;
import com.example.domainTelegram.domainTelegram.model.Domain;
import com.example.domainTelegram.domainTelegram.model.UrlData;
import com.example.domainTelegram.domainTelegram.repository.DomainRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class DomainService {
    private final DomainRepository domainRepository;
    private final UserService userService;


    private final BotHandler botHandler;
    private final RestTemplate restTemplate;




    public DomainService(DomainRepository domainRepository, RestTemplate restTemplate, UserService userService, BotHandler botHandler1) {
        this.domainRepository = domainRepository;
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.botHandler = botHandler1;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshAndSendDomains() {
        clearDomains();
        fetchDataAndSave();
        sendDomains();
    }


    private void sendDomains() {
        String message = LocalDate.now() + ": Собрано " + countDomains() + " доменов.";
        List<Long> ids = userService.getAllIds();
        for (Long id: ids) {
            botHandler.sendMessage(id, message);
        }
    }

    private void clearDomains() {
        domainRepository.deleteAll();
    }

    private void saveDataToDatabase(UrlData[] domainDataList) {
        for (UrlData domainData : domainDataList) {
            Domain domain = convertToDomainEntity(domainData);
            domainRepository.save(domain);
        }
    }


    private void fetchDataAndSave() {
        String url = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";
        ResponseEntity<UrlData[]> response = restTemplate.getForEntity(url, UrlData[].class);
        UrlData[] urlDataList = response.getBody();

        if (urlDataList != null && urlDataList.length > 0) {
            saveDataToDatabase(urlDataList);
        } else {
            throw new RuntimeException("No data retrieved from the API.");
        }
    }



    private long countDomains() {
        return domainRepository.count();
    }

    private Domain convertToDomainEntity(UrlData urlData) {
        return new Domain(
                urlData.getDomainName(),
                urlData.getHotness(),
                urlData.getPrice(),
                urlData.getLinks(),
                urlData.getVisitors(),
                urlData.getOld(),
                urlData.getDeleteDate(),
                urlData.isBlock()
        );
    }
}
