package com.northeastern.pkotak.webdev.services;

import com.northeastern.pkotak.webdev.models.Topic;
import com.northeastern.pkotak.webdev.models.Widget;
import com.northeastern.pkotak.webdev.repositories.TopicRepository;
import com.northeastern.pkotak.webdev.repositories.WidgetRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets(){
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{id}")
    public Optional<Widget> findWidgetById(@PathVariable("id") int widgetId){
        return widgetRepository.findById(widgetId);
    }

    @GetMapping("/api/topic/{topicId}/widget")
    public List<Widget> findWidgetsForTopic(@PathVariable("topicId") int topicId){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if(optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            return topic.getWidgets();
        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/widget")
    public List<Widget> saveWidgets(@PathVariable("topicId") int topicId, @RequestBody List<Widget> widgets){
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if(optionalTopic.isPresent()){
            Topic topic = optionalTopic.get();
            for(Widget w : widgets){
                w.setTopic(topic);
                widgetRepository.save(w);
            }
            return (List<Widget>) widgetRepository.findAll();
        }
        return null;
    }

    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(@PathVariable("widgetId") int widgetId,@RequestBody Widget updatedWidget){
        Optional<Widget> optionalWidget = widgetRepository.findById(widgetId);
        if(optionalWidget.isPresent()){
            Widget widget = optionalWidget.get();
            //TODO: add set Method in widget Model
            return widget;
        }
        return null;
    }

    @GetMapping("/api/image/search/{query}")
    public List<String> call_me(@PathVariable("query") String searchQuery) throws Exception {
        String url = "https://www.googleapis.com/customsearch/v1?key="+System.getenv("GOOGLE_API_KEY")+"&q="+searchQuery+"&searchType=image";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray jsonArray = myResponse.getJSONArray("items");
        List<String> links = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            links.add(object.getString("link"));
        }
        return links;
    }
}
