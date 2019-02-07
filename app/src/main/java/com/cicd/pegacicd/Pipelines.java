package com.cicd.pegacicd;

public class Pipelines {

    private String pipelinename;

    public Pipelines(String pipelinename)
    {
       this.setPipelinename(pipelinename);
    }


    public String getPipelinename() {
        return pipelinename;
    }

    public void setPipelinename(String pipelinename) {
        this.pipelinename = pipelinename;
    }
}
