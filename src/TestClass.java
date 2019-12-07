import java.util.Random;

public class TestClass {

    private Prod prod;

    @Before
    public void init()
    {
        prod=new Prod();
        System.out.println("Before executed");
    }

    @Test(priority = 10)
    public void TestMethod1()
    {
        System.out.println("Tested:"+prod.meth1());
    }

    @Test(priority = 9)
    public void TestMethod2()
    {
        System.out.println("Tested:"+prod.meth2());
    }

    @Test(priority = 8)
    public void TestMethod3()
    {
        System.out.println("Tested:"+prod.meth3());
    }

    @Test(priority = 7)
    public void TestMethod4()
    {
        System.out.println("Tested:"+prod.meth4());
    }

    @Test(priority = 6)
    public void TestMethod5()
    {
        System.out.println("Tested:"+prod.meth5());
    }

    @Test(priority = 5)
    public void TestMethod6()
    {
        System.out.println("Tested:"+prod.meth6());
    }

    @Test(priority = 4)
    public void TestMethod7()
    {
        System.out.println("Tested:"+prod.meth7());
    }

    @Test(priority = 3)
    public void TestMethod8()
    {
        System.out.println("Tested:"+prod.meth8());
    }

    @Test(priority = 2)
    public void TestMethod9()
    {
        System.out.println("Tested:"+prod.meth9());
    }

    @Test(priority = 1)
    public void TestMethod10()
    {
        System.out.println("Tested:"+prod.meth10());
    }

    @After
    public void finish()
    {
        System.out.println("Finalazing");
    }
}
