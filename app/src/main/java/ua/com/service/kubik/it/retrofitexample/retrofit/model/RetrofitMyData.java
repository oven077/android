package ua.com.service.kubik.it.retrofitexample.retrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RetrofitMyData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public boolean isFifo() {
        return isFifo;
    }

    @SerializedName("isFifo")
    @Expose
    private boolean isFifo;

    @SerializedName("link")
    @Expose
    private HashMap link;

    @SerializedName("mas")
    @Expose
    private ArrayList<HashMap> mas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RetrofitMyData{" +
                "iD='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}