package com.example.giangdam.selectiontime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Giang.Dam on 7/20/2015.
 */
public class JobInWeek {

    private String title;
    private String content;
    private Date datefinish;
    private Date timefinish;


    JobInWeek(){
        super();
    }

    JobInWeek(String title,String content,Date datefinish, Date timefinish)
    {
        this.title = title;
        this.content = content;
        this.datefinish = datefinish;
        this.timefinish = timefinish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatefinish() {
        return datefinish;
    }

    public void setDatefinish(Date datefinish) {
        this.datefinish = datefinish;
    }

    public Date getTimefinish() {
        return timefinish;
    }

    public void setTimefinish(Date timefinish) {
        this.timefinish = timefinish;
    }



    //get type date and time
    public String getDateFormat(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public String getTimeFormat(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a",Locale.getDefault());
        return  simpleDateFormat.format(date);
    }

    @Override
    public String toString()
    {
        return this.title + " - " + getDateFormat(this.datefinish) + " - " + getTimeFormat(this.timefinish);
    }
}
