package info.krushik.android.jsonretrofit.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoAnswer implements Parcelable {

    @SerializedName("list")
    private List<Video> list;

    public List<Video> getList() {
        return list;
    }

    public void setList(List<Video> list) {
        this.list = list;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    public VideoAnswer() {
    }

    protected VideoAnswer(Parcel in) {
        this.list = in.createTypedArrayList(Video.CREATOR);
    }

    public static final Parcelable.Creator<VideoAnswer> CREATOR = new Parcelable.Creator<VideoAnswer>() {
        @Override
        public VideoAnswer createFromParcel(Parcel source) {
            return new VideoAnswer(source);
        }

        @Override
        public VideoAnswer[] newArray(int size) {
            return new VideoAnswer[size];
        }
    };
}
