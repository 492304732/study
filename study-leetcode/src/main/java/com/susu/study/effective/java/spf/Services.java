package com.susu.study.effective.java.spf;

import java.util.HashMap;
import java.util.Map;

public class Services {
    private Services(){}    //保证该类是不可实例化的
    private static Map<String,Provider> providers=new HashMap<>();//一系列服务提供者
    private static String DEFAULT_PROVIDER_NAME="<def>";
    /**
     * 提供者注册API.(默认)
     * @param provider
     */
    public static void registDefaultProvider(Provider provider) {
        providers.put(DEFAULT_PROVIDER_NAME, provider);
    }
    /**
     * 提供者注册API
     * @param providerName
     * @param provider
     */
    public static void registProvider(String providerName,Provider provider) {
        providers.put(providerName, provider);
    }
    /**
     * 服务访问API,默认
     * @return
     */
    public static Service newService() {
        return providers.get(DEFAULT_PROVIDER_NAME).newService();
    }
    /**
     * 服务访问API.
     * @return
     */
    public static Service newService(String serviceName) {
        return providers.get(serviceName).newService();
    }
}