package com.susu.study.effective.java.spf;

public class WeekProvider implements Provider{

    @Override
    public Service newService() {
        return new WeekService();
    }

}