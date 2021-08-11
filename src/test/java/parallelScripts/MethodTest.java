package parallelScripts;

import org.testng.annotations.Test;

public class MethodTest {
  @Test(threadPoolSize = 2, invocationCount = 4, timeOut = 1000)
  public void methodOne() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Method One : "+ id);
  }
  @Test
  public void methodTwo() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Method Two : "+ id);
  }
  @Test
  public void methodThree() {
	  long id = Thread.currentThread().getId();
	  System.out.println("Method Three : "+ id);
  }
}
