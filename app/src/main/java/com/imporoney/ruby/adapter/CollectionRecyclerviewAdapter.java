package com.imporoney.ruby.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imporoney.ruby.R;
import com.imporoney.ruby.application.MyApplication;
import com.imporoney.ruby.modules.Thing;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Zero on 11/27/2015.
 */
public class CollectionRecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Thing> things;

    public CollectionRecyclerviewAdapter(Context context, List<Thing> things) {
        this.context = context;
        this.things = things;
    }

    public CollectionRecyclerviewAdapter(Context context) {
        this.context = context;
    }

    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHodler(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHodler) {
            ((MyViewHodler) holder).name.setText(things.get(position).getName());
            Picasso.with(context).load(MyApplication.baseUrl + things.get(position).getHeadlogo().getUrl())
                    .into(((MyViewHodler) holder).headlogo);
            ((MyViewHodler) holder).phone.setText("联系方式：" + things.get(position).getPhone());
            ((MyViewHodler) holder).address.setText("位置：" + things.get(position).getAddress());

        }
    }

    @Override
    public int getItemCount() {
        return things.size();
    }



    class MyViewHodler extends RecyclerView.ViewHolder {

        @Bind(R.id.name)
        TextView name;
        @Bind(R.id.phone)
        TextView phone;
        @Bind(R.id.address)
        TextView address;
        @Bind(R.id.headlogo)
        ImageView headlogo;

        public MyViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyImageHodler extends RecyclerView.ViewHolder {
        @Bind(R.id.aty_lan)
        ImageView atyImage;

        public MyImageHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
