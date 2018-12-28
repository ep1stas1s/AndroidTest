package com.yjp.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {

    Context context;

    ArrayList<SingerItem> items = new ArrayList<>();

    public SingerAdapter(Context context) {
        this.context = context;
    }

    // item 의 개수
    @Override
    public int getItemCount() {
        return items.size();
    }

    // 처음 만들어졌을 때 (on 은 자동으로 호출됨)
    @NonNull
    @Override
    public SingerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // View Holder 가 재사용 되는 환경이면 이 method 는 호출되지 않음
        // 때문에 새로 viewHolder 객체를 생성
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // parameter 로 받은 viewGroup 은 xml 레이아웃의 최상위 레이아웃
        View itemView = inflater.inflate(R.layout.singer_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    // View와 결합될 때 (on 은 자동으로 호출됨)
    @Override
    public void onBindViewHolder(@NonNull SingerAdapter.ViewHolder viewHolder, int position) {
        // recyclerView 에서 몇 번째 item을 보여주는 지
        SingerItem item = items.get(position);
        viewHolder.setItem(item);

        // listener 추가
        viewHolder.setOnItemClickListner(listener);
    }




    // 수동으로 item을 추가하는 method
    public void addItem(SingerItem item){
        items.add(item);
    }

    public void addItmes(ArrayList<SingerItem> items){
        this.items = items;
    }

    public SingerItem getItem(int position){
        return items.get(position);
    }



    // RecyclerView click event (listener)
    OnItemClickListener listener;

    // RecyclerView click event (interface)
    public static interface  OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);

    }

    // item 클릭 시 event
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }




    // viewHolder class
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;

        OnItemClickListener listener;

        // viewHolder : item 을 담고 있는 holder
        // 여기서 View 에는 (singer_item.xml 의) textView, textView2 가 들어가 있음
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }
        // View <-> 실제 data 매칭
        public void setItem(SingerItem item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }

        public void setOnItemClickListner(OnItemClickListener listener){
            this.listener = listener;
        }
    }
}