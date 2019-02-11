package com.cicd.pegacicd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SelectedPipeline extends AppCompatActivity {

    String pipelinelabel;
    TextView pipelinetxtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_pipeline);

        Bundle pipelineInfo = getIntent().getExtras();
        pipelinelabel = pipelineInfo.getString("PipeLineName");

        Log.e("name of pipe ",pipelinelabel);

        pipelinetxtview = findViewById(R.id.Pipeline_label);
        pipelinetxtview.setText(pipelinelabel);

    }
}
