package com.northeastern.pkotak.webdev.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private String name;
    private String type;
    private String style;
    private String listItems;
    private String src;
    private String href;
    private int size;
    private Boolean ordered;
    private int position;
    private String youtubeIframe;
    private String docIframe;
    private String slidesIframe;
    @Lob
    @Column(name = "html_content", length = 512)
    private String htmlContent;
    @ManyToOne
    @JsonIgnore
    private Topic topic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getListItems() {
        return listItems;
    }

    public void setListItems(String listItems) {
        this.listItems = listItems;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getYoutubeIframe() {
        return youtubeIframe;
    }

    public void setYoutubeIframe(String youtubeIframe) {
        this.youtubeIframe = youtubeIframe;
    }

    public String getDocIframe() {
        return docIframe;
    }

    public void setDocIframe(String docIframe) {
        this.docIframe = docIframe;
    }

    public String getSlidesIframe() {
        return slidesIframe;
    }

    public void setSlidesIframe(String slidesIframe) {
        this.slidesIframe = slidesIframe;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
