package info.krushik.android.jsonretrofit.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoAnswer  {

    @SerializedName("list")
    private ArrayList<Video> list;

    public ArrayList<Video> getList() {
        return list;
    }

    public void setList(ArrayList<Video> list) {
        this.list = list;
    }
}
