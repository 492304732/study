package com.susu.study.effective.java.spf;

public class TestService {
    public void testService() {
        Services.registProvider("week",new WeekProvider());
        Services.registProvider("strong", new StrongProvider());
        Services.registDefaultProvider(new StrongProvider());
        Services.newService().doService();
        Services.newService("week").doService();
    }
}