package info.krushik.android.jsonretrofit.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import info.krushik.android.jsonretrofit.R;
import info.krushik.android.jsonretrofit.model.ModelVideo;

public class ModelsListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ModelVideo> modelVideos;

    public ModelsListRecyclerAdapter(ArrayList<ModelVideo> modelVideos) {
        this.modelVideos = modelVideos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);
        return new ViewHolderHotel(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderHotel holderHotel = (ViewHolderHotel) holder;
        ModelVideo modelVideo = modelVideos.get(position);

        holderHotel.title.setText(modelVideo.getTitle());
//        holderHotel.text.setText(modelVideo.getPicture());
    }

    @Override
    public int getItemCount() {
        return modelVideos.size();
    }

    public static class ViewHolderHotel extends RecyclerView.ViewHolder {
        public TextView title, text;

        public ViewHolderHotel(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
//            text = (TextView) v.findViewById(R.id.text);
        }
    }
}