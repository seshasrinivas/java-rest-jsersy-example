package com.itc.robot.hover.rest.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONResponceModel {

    @SerializedName("coords")
    @Expose
    private List<Integer> coords = null;
    @SerializedName("patches")
    @Expose
    private Integer patches;


    public List<Integer> getCoords() {
        return coords;
    }

    public void setCoords(List<Integer> coords) {
        this.coords = coords;
    }

    public Integer getPatches() {
        return patches;
    }

    public void setPatches(Integer patches) {
        this.patches = patches;
    }
}