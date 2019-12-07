import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestEngine {
   private static class TestPriority {
       TestPriority(Method method, int priority) {
           this.method = method;
           this.priority = priority;
       }

       Method method;
       Integer priority;

       Integer getPriority() {
           return priority;
       }
   }

    public static void main(String[] args) {
        Class clazz=TestClass.class;
        try {
            Constructor con = clazz.getConstructor();
            Object test=con.newInstance();
            Method[] methods=clazz.getMethods();
            String beforeErr="before";
            String afterErr="after";
            List<Method> before=new ArrayList<>();
            List<Method> after=new ArrayList<>();
            Comparator<TestPriority> comparator=(o1, o2) ->o1.getPriority().compareTo(o2.getPriority());
                      Queue<TestPriority> testMethods=new PriorityQueue<>(10, comparator);
            for (Method meth:methods)
            {
                if (meth.isAnnotationPresent(Before.class)) before.add(meth);
                if (meth.isAnnotationPresent(After.class)) after.add(meth);
                if (meth.isAnnotationPresent(Test.class)) {
                    int priority=meth.getAnnotation(Test.class).priority();
                    testMethods.add(new TestPriority(meth, priority));
                }
            }
            if (before.size()!=1) throw new RuntimeException(beforeErr);
            if (after.size()!=1) throw new RuntimeException(afterErr);
            before.get(0).invoke(test);
            while (testMethods.peek()!=null)
            {
                testMethods.poll().method.invoke(test);
            }
            after.get(0).invoke(test);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e)
        {
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e)
        {
            System.out.println("Check "+e.getMessage()+" annotations");
        }
    }
}
