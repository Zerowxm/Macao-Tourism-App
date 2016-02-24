package com.imporoney.ruby.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.imporoney.ruby.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zero on 11/27/2015.
 */
public class CardRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    public CardRecyclerviewAdapter(Context context) {
        this.context=context;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==0){
            return new MyImageHodler(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.colletion_item_bar,parent,false
            )) ;
        }else {
            return new MyViewHodler(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item,parent,false
            ));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyImageHodler){
//            ((MyImageHodler) holder).atyImage.setImageDrawable(
//                    ContextCompat.getDrawable(context,R.drawable.aty_intro));
            //Picasso.with(context).load(R.drawable.aty_intro).into(((MyImageHodler) holder).atyImage);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    @Override
    public int getItemViewType(int position) {
        return position==0?0:1;
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        public MyViewHodler(View itemView) {
            super(itemView);
        }
    }

    class MyImageHodler extends RecyclerView.ViewHolder{
        public MyImageHodler(View itemView) {
            super(itemView);
        }
    }
}
