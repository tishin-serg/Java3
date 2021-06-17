package homework_g;

public class DemoClass {

    @AfterSuite
    public static void method1() {
        System.out.println("Method 1");
    }

    @Test(value = 10)
    public static void method2() {
        System.out.println("Method 2");
    }

    @Test(value = 9)
    public static void method3() {
        System.out.println("Method 3");
    }

    @BeforeSuite
    public static void method5() {
        System.out.println("Method 5");
    }

    @Test(value = 1)
    public static void method4() {
        System.out.println("Method 4");
    }

}
