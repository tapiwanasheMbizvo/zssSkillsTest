package com.tapiwanashe.zssSkillsTest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapiwanashe.zssSkillsTest.models.*;
import com.tapiwanashe.zssSkillsTest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/purchase")
public class ShopController {

    private final RestTemplate restTemplate;

    public ShopController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}/{cardID}")
    @ResponseStatus(HttpStatus.OK)
    private String getOneBook(@PathVariable(name = "id") Long id, @PathVariable(name = "cardID") String cardID){
        Optional<Book> bookToBuy = bookRepository.findById(id);

        return  doPost(bookRepository.findById(id).get().getPrice(), cardID);

       /* bookToBuy.ifPresent(book ->
                doPost(book.getPrice(), cardID)
                );
*/

    }


    String doPost (Double price, String cardID){
        Card card = new Card();
        card.setId(cardID);
        //card.setExpiry(new Date("2022-03-15"));
        card.setExpiry(LocalDate.now().plusYears(2));
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd’T’HH:mm:ss.SSSZ");
        HashMap<String, Object> additionalData = new HashMap<>();
        additionalData.put("SampleKey", "THis is a Sample value");

        String url = "https://lab.v.co.zw/interview/api/transaction";

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("9ca3d5ed-dc04-4700-8dd6-7d60c3cdf0fa");
        headers.setContentLength(358);
       // headers.setHost(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress()));


        TransactionRequest transactionRequest = new TransactionRequest();

        transactionRequest.setTransactionType("PURCHASE");
        transactionRequest.setExtendedType("NONE");
        transactionRequest.setAmount(price);
        transactionRequest.setCreated(LocalDateTime.now());
        transactionRequest.setCard(card);
        transactionRequest.setReference(getReference());
        transactionRequest.setNarration("BOOK PURCHASE");
        transactionRequest.setAdditionalData(additionalData);


        Map<String, Object> map = new HashMap<>();

        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        map.put("type" , transactionRequest.getTransactionType());
        map.put("extendedType", transactionRequest.getExtendedType());
        map.put("amount", transactionRequest.getAmount());
        map.put("created", "2021-06-10T13:22:30.746+02:00");
        map.put("reference", transactionRequest.getReference());
        map.put("narration", transactionRequest.getNarration());
        map.put("additionalData", additionalData);
        Map<String, Object> cardPayLoad = new HashMap<>();
        cardPayLoad.put("id", card.getId());
        cardPayLoad.put("expiry", "2020-01-01");
        map.put("card", cardPayLoad);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);

        ResponseEntity<TransactionResponse> response = restTemplate.postForEntity(url, entity, TransactionResponse.class);

        System.out.println("_______________________________________________________________________________");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().getResponseCode());
        System.out.println(response.getBody().getDebitReference());
        System.out.println(response.getBody().getResponseDescription());
        System.out.println("_______________________________________________________________________________");


        Map<String, Object> purchaseResponse = new HashMap<>();

        if(response.getBody().getResponseCode().equals("000")){

            purchaseResponse.put("success", true);
        }else{
            purchaseResponse.put("description", response.getBody().getResponseDescription());
            purchaseResponse.put("success", false);
        }


        try {
            return objectMapper.writeValueAsString(purchaseResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "general failed";
    }

    private String getReference() {
        return UUID.randomUUID().toString();
    }

}
