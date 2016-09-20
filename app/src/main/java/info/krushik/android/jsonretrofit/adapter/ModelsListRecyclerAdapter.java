package info.krushik.android.jsonretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import info.krushik.android.jsonretrofit.R;
import info.krushik.android.jsonretrofit.model.Video;

public class ModelsListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Video> videos;

    public ModelsListRecyclerAdapter(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);
        return new ViewHolderHotel(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderHotel holderHotel = (ViewHolderHotel) holder;
        Video video = videos.get(position);

        holderHotel.title.setText(video.getTitle());
        holderHotel.picture.setText(video.getPicture());
        holderHotel.desc.setText(video.getDesc());
        holderHotel.length.setText(video.getLength());
        holderHotel.dt.setText(video.getDt());
        holderHotel.video.setText(video.getVideo());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class ViewHolderHotel extends RecyclerView.ViewHolder {
        public TextView title, picture, desc, length, dt, video;

        public ViewHolderHotel(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            picture = (TextView) v.findViewById(R.id.picture);
            desc = (TextView) v.findViewById(R.id.desc);
            length = (TextView) v.findViewById(R.id.length);
            dt = (TextView) v.findViewById(R.id.dt);
            video = (TextView) v.findViewById(R.id.video);
        }
    }
}