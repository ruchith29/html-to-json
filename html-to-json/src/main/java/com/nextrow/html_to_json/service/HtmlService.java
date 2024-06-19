package com.nextrow.html_to_json.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class HtmlService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map<String,Object> generateHtmlToJson(Document document) throws IOException {

        Elements headers = document.select("h1,h2,h3,h4,h5,h6");

        List<Map<String,String>> headData=new ArrayList<>();

        for(Element head:headers){
            Map<String,String> headings=new LinkedHashMap<>();
            headings.put("tag",head.tagName());
            headings.put("content",head.text());

            for (Attribute attribute : head.attributes()) {
                headings.put(attribute.getKey(), attribute.getValue());
            }
            headData.add(headings);
        }

        Map<String,Object> totalData = new LinkedHashMap<>();
        totalData.put("headings", headData);

        mongoTemplate.insert(totalData,"html-content");
        return totalData;
    }
}
