package com.susu.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1、扫描包下的controller注解类
 * 2、扫描controller类型下RequestMapping注解的方法
 * 3、获取方法的请求参数类，并获取参数类中的非静态参数
 */
public class ApiWikiTest {


    public static void main(String[] args) {
        //获取包下的controller类
        Set<Class<?>> classList = AnnoManageUtil.getPackageController("com.susu.util", true);
        List<ExecutorBean> beanList = new ArrayList<ExecutorBean>();
        for (Class cls : classList) {
            //获取类下的requestMappinp方法
            Map<String, ExecutorBean> map = AnnoManageUtil.getRequestMappingMethod(cls);
            if (map == null) {
                continue;
            }
            for (String key : map.keySet()) {
                ExecutorBean bean = map.get(key);
                //获取方法的请求参数类
                Class paramClass = AnnoManageUtil.getParamByMethod(bean.getMethod());
                if (paramClass != null) {
                    bean.setParamClassName(paramClass.getSimpleName());
                }
                //获取请求参数类的具体参数
                List<String> paramNameList = AnnoManageUtil.getParamByClass(paramClass);
                List<ParamVO> paramVOList = AnnoManageUtil.getParamVOByClass(paramClass);
                bean.setParamNameList(paramNameList);
                bean.setParamVOList(paramVOList);
                bean.setUrl(key);
                beanList.add(bean);
            }
        }

        int i = 1;
        //输出wiki文档
        for (ExecutorBean bean : beanList) {
            int num = i++;
            String url = bean.getUrl();
            String packageName = bean.getPackageName();
            List<String> paramNameList = bean.getParamNameList();
            List<ParamVO> paramVOList = bean.getParamVOList();
            Method method = bean.getMethod();
            AnnoManageUtil.print(num + "%s", url);
            AnnoManageUtil.print("---");
            AnnoManageUtil.print("###%s.1接口说明%s", num + "", url);
            AnnoManageUtil.print("|包名|类名|方法名|参数名|");
            AnnoManageUtil.print("|-----------|-----------|-----------|-----------|");
            AnnoManageUtil.print("|%s|%s|%s|%s|", packageName, bean.getClassName(),
                    method.getName(), bean.getParamClassName());
            AnnoManageUtil.print("###%s.2输入参数", num + "");
            AnnoManageUtil.print("|名称|类型");
            AnnoManageUtil.print("|-----------|-----------|");
            for (ParamVO paramVO : paramVOList) {
                AnnoManageUtil.print("|%s|%s|", paramVO.getParamName(), paramVO.getParamType());

            }
        }


    }
}