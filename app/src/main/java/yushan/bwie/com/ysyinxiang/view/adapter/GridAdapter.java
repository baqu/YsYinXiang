package yushan.bwie.com.ysyinxiang.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.bean.AllTopics_Bean;

/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {


    List<AllTopics_Bean.DataBean.TopicBean> list;
    Context context;

    public GridAdapter(List<AllTopics_Bean.DataBean.TopicBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item3, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(GridAdapter.ViewHolder holder, int position) {

        holder.title.setText(list.get(position).getTitle());

        Glide.with(context).load(list.get(position).getLogo()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        public ViewHolder(View itemView) {
            super(itemView);


            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
