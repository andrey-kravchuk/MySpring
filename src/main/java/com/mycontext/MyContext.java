package com.mycontext;

import com.myAnnotation.MyService;
import com.myspring.MySpring;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class MyContext implements MySpring {

    private volatile static Map context;

    static {
        context = new ConcurrentHashMap();
    }

    @Override
    public void analyze(){
        ArrayList<URL> urls = new ArrayList<URL>();
        ClassLoader [] classloaders = {
                getClass().getClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
        for (int i = 0; i < classloaders.length; i++) {
            if (classloaders[i] instanceof URLClassLoader) {
                urls.addAll(Arrays.asList(((URLClassLoader)classloaders[i]).getURLs()));
            }
            else {
                throw new RuntimeException("classLoader is not an instanceof URLClassLoader");
            }


        }

        String EXTERNAL_PACKAGE = "homemyspring";
        String filter = "com.*";
        FilterBuilder filters = null;
        if (System.getProperty(EXTERNAL_PACKAGE) != null) {
            String userFilter = System.getProperty(EXTERNAL_PACKAGE);
            filters = new FilterBuilder().include(userFilter).include(filter);
        }
        else {
            filters = new FilterBuilder().include(filter);
        }
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.filterInputsBy(filters);
        configurationBuilder.setUrls(urls);
        Reflections reflections = new Reflections(configurationBuilder);

        System.out.println(reflections.getStore().getClass());
//        Set<String> myclassnames = reflections.getStore().getTypesAnnotatedWith("MyService");
//        for (Iterator<String> iterator = myclassnames.iterator(); iterator.hasNext();) {
//            String myName = (String) iterator.next();
//            Class<?> myClass = null;
//            try {
//                myClass = Class.forName(myName, true, Thread.currentThread().getContextClassLoader() );
//            }
//            catch (ClassNotFoundException e) {
//                try {
//                    myClass = Class.forName(myName);
//                }
//                catch (ClassNotFoundException f) {
//                    LOG.error("exception caught:", e);
//                    LOG.error("my {} is not working!", myName);
//                }
//            }
//            HuepInterface huep = (HuepInterface)parserClass.newInstance();
//        }




    }

    @Override
    public Map getBeans() {
        return context;
    }
}
