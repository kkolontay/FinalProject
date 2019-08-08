package com.udacity.gradle.builditbigger.test;

import com.udacity.gradle.builditbigger.backend.jokeApi.model.MyBean;

import org.junit.Test;

public class ApiEndPointsTest {
    @Test
    public void verifyBeanResponse() {
        MyBean bean = new MyBean();
        bean.setData("string");
        assert bean.getData().equals("string");
    }
}
