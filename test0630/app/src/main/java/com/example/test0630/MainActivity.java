package com.example.test0630;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    //繼承appcompatactivity可直接使用function

    private RecyclerView mRecyclerView; //container來存放所有子view

    //butter knife


    @Override //複寫註解
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //呼叫母類別(AppCompatActivity)的onCreate function
        setContentView(R.layout.activity_main); //把activity_main這個layout設為MainActivity的畫面


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_competitions);
        //recyclerview_competitions是container來存放view


        new FirebaseDatabaseHelper().readCompetition(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsloaded(List<Competition> competitions, List<String> keys) {
                new RecyclerView_config().setConfig(mRecyclerView, MainActivity.this, competitions, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

//        Button com_data = findViewById(R.id.b2);
//        com_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, test.class);
//                startActivity(intent);
//            }
//
//        });


    }



}