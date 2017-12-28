package yushan.bwie.com.ysyinxiang.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.bean.Recommended_list;
import yushan.bwie.com.ysyinxiang.view.view.Details_page;

/**
 * 类的用途：推荐首页适配器
 * 作者：张佳乐
 * 时间：2017/12/26AM8:45
 */

public class RecomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    List<Recommended_list.DataBean.ArticleBean> list;
    Context context;
    private OnItemClickListener mItemClickListener;

    //定义两种常量  表示三种条目类型
    int type_1 = 0;
    int type_2 = 1;
    int type_3 = 2;


    //记录下标;
    public int position;



    public RecomAdapter(List<Recommended_list.DataBean.ArticleBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == 0) {
            View v = View.inflate(context, R.layout.recommend_item, null);
            ViewHolderOne viewHolderOne = new ViewHolderOne(v);

            v.setOnClickListener(this);
            return viewHolderOne;
        } else if (viewType ==1){
            //找到布局文件
            View vv = View.inflate(context, R.layout.recommend_item2, null);
            ViewHolderTwo viewHolderTwo = new ViewHolderTwo(vv);
            return viewHolderTwo;
        }else {

            View vvv = View.inflate(context, R.layout.recommend_item3, null);
            ViewHolderthree viewHolderthree=new ViewHolderthree(vvv);

            return viewHolderthree;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolderOne){
            final ViewHolderOne viewholderone= (ViewHolderOne) holder;

            viewholderone.time.setText(list.get(position).getTitle());
            viewholderone.pinglun.setText(list.get(position).getComment_count());
            viewholderone.zhichi.setText(list.get(position).getUpvote());
            viewholderone.time.setText(list.get(position).getCreate_time());

            Glide.with(context).load(list.get(position).getFace()).into(viewholderone.image);

            viewholderone.itemView.setTag(position);


            viewholderone.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, Details_page.class);

                    intent.putExtra("image",list.get(position).getFace());
                    intent.putExtra("title",list.get(position).getTitle());
                    intent.putExtra("neirong",list.get(position).getSummary());
                    context.startActivity(intent);
                }
            });



        }else if (holder instanceof ViewHolderTwo) {

            final ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;

            viewHolderTwo.pinglun2.setText(list.get(position).getComment_count());
            viewHolderTwo.zhichi2.setText(list.get(position).getUpvote());
            viewHolderTwo.title2.setText(list.get(position).getTitle());
            viewHolderTwo.time2.setText(list.get(position).getCreate_time());


            Glide.with(context).load(list.get(position).getFace()).into(viewHolderTwo.image2);
            Glide.with(context).load(list.get(position).getFace()).into(viewHolderTwo.image1);
            Glide.with(context).load(list.get(position).getFace()).into(viewHolderTwo.image3);

        }

//        }else if (holder instanceof ViewHolderthree){
//
//            final ViewHolderthree viewHolderthree= (ViewHolderthree) holder;
//
//
//
//
//
//        }



    }

    //多条目需要重写的方法
    @Override
    public int getItemViewType(int position) {
        if (position==type_1) {
            return 0;
        } else if (position==type_2){
            return 1;
        }else {
            return 0;
        }




    }


    @Override
    public int getItemCount() {

        return list.size();
    }




    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {

        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer) v.getTag());
        }

    }

    class ViewHolderOne extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, pinglun, zhichi, time;

        public ViewHolderOne(View itemView) {
            super(itemView);
//            this.mView = itemView;
            image = (ImageView) itemView.findViewById(R.id.item_img);
            title = (TextView) itemView.findViewById(R.id.text);
            pinglun = (TextView) itemView.findViewById(R.id.pinglun);
            zhichi = (TextView) itemView.findViewById(R.id.zhichi);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {

        TextView title2, pinglun2, zhichi2, time2;
        ImageView image2,image1,image3;

        public ViewHolderTwo(View itemView) {
            super(itemView);

            title2 = (TextView) itemView.findViewById(R.id.text2);
            image2 = (ImageView) itemView.findViewById(R.id.item_img2);
            image1 = (ImageView) itemView.findViewById(R.id.item_img1);
            image3 = (ImageView) itemView.findViewById(R.id.item_img3);
            pinglun2 = (TextView) itemView.findViewById(R.id.pinglun2);
            zhichi2 = (TextView) itemView.findViewById(R.id.zhichi2);
            time2 = (TextView) itemView.findViewById(R.id.time2);
        }
    }

    class ViewHolderthree extends RecyclerView.ViewHolder{

        RecyclerView grid;
        public ViewHolderthree(View itemView) {
            super(itemView);

             grid = (RecyclerView) itemView.findViewById(R.id.grid);
        }
    }



    //返回position的方法;
    public  int getPosition(){
        return position;
    }
   /* private CallPos mCallPos;
    public  interface  CallPos{
        void callPosition(int position);
    }*/
}
