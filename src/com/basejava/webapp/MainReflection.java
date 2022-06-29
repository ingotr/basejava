package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("Name");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);

        Method method = r.getClass().getMethod("toString");
        method.setAccessible(true);
        System.out.println("invoked toString method: " + method.invoke(r));
        System.out.println("invoked toString method: " + method);
    }
}
