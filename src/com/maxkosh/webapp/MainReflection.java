package com.maxkosh.webapp;

import com.maxkosh.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume resume = new Resume();

        Field field = resume.getClass().getDeclaredFields()[0];
        System.out.println(field.getName());

        System.out.println("Initial resume UUID: " + resume);
        field.setAccessible(true);
        field.set(resume, "new_uuid");
        System.out.println("Assigned resume UUID: " + resume);
        field.setAccessible(false);

        Method[] methods = resume.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("toString")) {
                System.out.println("resume.toString via reflection_v1: " + method.invoke(resume));
            }
        }

        Method method = resume.getClass().getMethod("toString");
        System.out.println("resume.toString via reflection_v2: " + method.invoke(resume));
    }
}
