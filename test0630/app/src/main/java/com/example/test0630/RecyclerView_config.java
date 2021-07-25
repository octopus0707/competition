package com.example.test0630;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerView_config {
    private Context mContext;
    private CompetitionsAdapter mCompetitionsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Competition> competitions, List<String> keys){
        mContext = context;
        mCompetitionsAdapter = new CompetitionsAdapter(competitions, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCompetitionsAdapter);
    }

    class CompetitionitemView extends RecyclerView.ViewHolder{
        //ViewHolder存放view reference的地方

        String temp;
        private ImageView com_pic;
        private String key;

        //private TextView mCid;
        private TextView mTitle;
        private TextView mPrize;
        private TextView mDeadline;
        private TextView mPeople;
        private Button button;


        public CompetitionitemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.competition_list_item, parent, false));
            //mCid = (TextView) itemView.findViewById(R.id.com_button);
            mTitle = (TextView) itemView.findViewById(R.id.title_txtView);
            mPrize = (TextView) itemView.findViewById(R.id.prize_txtView);
            mDeadline = (TextView) itemView.findViewById(R.id.deadline_txtView);
            mPeople = (TextView) itemView.findViewById(R.id.people_txtView);
        }

        public void bind(Competition competition, String key){
            //mCid.setText(competition.getCid());
            mTitle.setText(competition.getTitle());
            mPrize.setText(competition.getPrize());
            mDeadline.setText(competition.getDead_line());
            mPeople.setText(competition.getPeople());

            //抓取網路照片 用到github bumptech/glide 要記得在manifest加premession Internet
            temp = competition.getPic();
            com_pic = (ImageView) itemView.findViewById(R.id.com_pic);
            Glide.with(mContext).load(temp).into(com_pic);

            this.key = key;
        }

    }
    class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionitemView>{
        private List<Competition> mCompetitionList;
        private List<String> mKeys;

        public CompetitionsAdapter(List<Competition> mCompetitionList, List<String> mKeys) {
            this.mCompetitionList = mCompetitionList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public CompetitionitemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CompetitionitemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CompetitionitemView holder, int position) {
            holder.bind(mCompetitionList.get(position), mKeys.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG","onClick: clicked on: " + mKeys.get(position));

//                    Toast.makeText(mContext, mKeys.get(position), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, test.class);
                    intent.putExtra("url", mKeys.get(position));
//                    intent.putExtra("name", mContext.get(position));
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCompetitionList.size();
        }
    }
}