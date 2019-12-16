package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Resume resume = new Resume();

        Field field = resume.getClass().getDeclaredFields()[0];
        Method method = resume.getClass().getDeclaredMethods()[1];

        System.out.println(method.getName());
        System.out.println(method.invoke(resume));

        System.out.println(field.getName());
        field.setAccessible(true);
        System.out.println(resume);
        field.set(resume, "new_uuid");
        System.out.println(resume);
    }
}
