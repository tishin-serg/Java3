package homework_g;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestExecute {

    public static void main(String[] args) {
        Class demoClass = DemoClass.class;
        try {
            start(demoClass);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static void start(Class testClass) throws InvocationTargetException, IllegalAccessException {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = testClass.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                methodList.add(m);
            }
        }
        methodList.sort(Comparator.comparingInt((Method m) -> m.getAnnotation(Test.class).value()).reversed());
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methodList.size() > 0 && methodList.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("Аннотаций @BeforeSuite, больше чем 1");
                }
                methodList.add(0, m);
            }
        }
        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methodList.size() > 0 && methodList.get(methodList.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("Аннотаций @AfterSuite, больше чем 1");
                }
                methodList.add(methodList.size(), m);
            }
        }
        for (Method m : methodList) {
            m.invoke(null);
        }
    }
}
