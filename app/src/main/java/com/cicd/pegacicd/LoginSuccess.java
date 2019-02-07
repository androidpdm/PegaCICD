package com.cicd.pegacicd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginSuccess extends AppCompatActivity {

    String PipeLineInfo;
    JSONObject jsonObject;
    JSONArray jsonArray;
    PipelineAdapter pipelineAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        listView = (ListView) findViewById(R.id.PipeLineListView);



        pipelineAdapter = new PipelineAdapter(LoginSuccess.this,R.layout.row_layout);

        listView.setAdapter(pipelineAdapter);

        Bundle bundlelogin = getIntent().getExtras();
        PipeLineInfo = bundlelogin.getString("PipelineResponse");
        Log.e("Json in second activity: ",PipeLineInfo);

        try {
            jsonObject  =  new JSONObject(PipeLineInfo);
            jsonArray = jsonObject.getJSONArray("pipelines");

            int count = 0;
            String pipeline;

            while (count<jsonArray.length())
            {
              JSONObject PInfo =  jsonArray.getJSONObject(count);
              pipeline = PInfo.getString("pipelineName");
              Pipelines pipelines = new Pipelines(pipeline);

              pipelineAdapter.add(pipelines);

              count++;
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
