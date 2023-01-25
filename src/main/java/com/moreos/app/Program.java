package com.moreos.app;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("Hello World");
        printClasses();
        Class<?> classPath = printClassesDeclarations();
        Object obj = createNewClass(classPath);
        updateObjectValues(obj);
        invokeMethod(obj);
    }

    public static void printClasses() {
        System.out.println("Classes:");
        System.out.println("\tUser");
        System.out.println("\tPhone");
        System.out.println("--------------------------------------------------------");
    }

    public static Class<?> printClassesDeclarations() {
        System.out.println("Enter class name:");
        String className = scanner.next();
        System.out.println("--------------------------------------------------------");
        Class<?> classByName = null;
        try {
            classByName = Class.forName("edu.school21.models." + className);
            System.out.println("Fields:");
            for (Field f : classByName.getDeclaredFields()) {
                System.out.println("\t" + f.getType().getSimpleName() + " " + f.getName());
            }
            System.out.println("methods:");
            for (Method m : classByName.getDeclaredMethods()) {
                if (!m.getName().equals("toString")) {
                    System.out.print("\t" + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
                    for (Parameter p : m.getParameters()) {
                        System.out.print(p.getType().getSimpleName() + " ");
                    }
                    System.out.print("\b)\n");
                }
            }
            System.out.println("--------------------------------------------------------");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return classByName;
    }

    public static Object createNewClass(Class<?> currentClass) {
        System.out.println("Create class object");
        Object resultClass = null;

        Constructor<?>[] constructors = currentClass.getConstructors();
        Constructor<?> c = constructors[0];
        List<Object> objects = new ArrayList<>();

        for (Field f : currentClass.getDeclaredFields()) {
            System.out.println(f.getName() + ":");
            switch (f.getType().getSimpleName()) {
                case "Integer":
                    objects.add((Integer) scanner.nextInt());
                    break;
                case "String":
                    objects.add((String) scanner.next());
                    break;
                case "Double":
                    objects.add((Double) scanner.nextDouble());
                    break;
            }
        }
        try {
            resultClass = c.newInstance(objects.toArray());
            System.out.println("Object created " + resultClass.getClass().getSimpleName() + resultClass.toString());
            System.out.println("--------------------------------------------------------");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return resultClass;
    }

    public static Object updateObjectValues(Object o) throws IllegalAccessException {
        Field field = null;
        System.out.println("Updating object.\nEnter name of the field for changing:");
        try {
            field = o.getClass().getDeclaredField(scanner.next());
            field.setAccessible(true);

        } catch (NoSuchFieldException e) {
            System.err.println("No such field, try again.");
        }
        System.out.println("Enter (" + field.getType().getSimpleName() + ") value: ");
        switch (field.getType().getSimpleName()) {
            case "Integer":
                field.set(o, scanner.nextInt());
                break;
            case "String":
                field.set(o, scanner.next());
                break;
            case "Double":
                field.set(o, scanner.nextDouble());
                break;
        }
        System.out.println("Object updated: " + o.toString());
        System.out.println("--------------------------------------------------------");
        return (o);
    }

    public static Object invokeMethod(Object o) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Enter name of method to invoke:");
        String methodStr = scanner.next();
        for (Method m : o.getClass().getDeclaredMethods()) {
            if (m.getName().equals(methodStr)) {
                Parameter[] parameters;
                List<Object> parsedParams = new ArrayList<>();
                parameters = m.getParameters();
                for (Parameter parameter : parameters) {
                    System.out.println("Enter " + parameter.getType().getSimpleName() + " value: ");
                    switch (parameter.getType().getSimpleName()) {
                        case "Integer":
                            parsedParams.add(scanner.nextInt());
                            break;
                        case "String":
                            parsedParams.add(scanner.next());
                            break;
                        case "Double":
                            parsedParams.add(scanner.nextDouble());
                            break;
                    }
                }
                Object returnValue = m.invoke(o, parsedParams.toArray());
                if (returnValue != null) {
                    System.out.println("Method returned: " + returnValue);
                }
            }
            System.out.println("--------------------------------------------------------");
        }

        return o;
    }
}
