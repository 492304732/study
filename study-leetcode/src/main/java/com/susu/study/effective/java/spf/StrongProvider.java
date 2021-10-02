package com.susu.study.effective.java.spf;

public class StrongProvider implements Provider{

    public Service newService() {
        return new StrongService();
    }
}