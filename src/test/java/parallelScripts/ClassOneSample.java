package parallelScripts;

import org.testng.annotations.Test;

public class ClassOneSample {
	@Test
	  public void methodOne() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Class One : "+ id);
	  }
	  @Test
	  public void methodTwo() {
		  long id = Thread.currentThread().getId();
		  System.out.println("class Two : "+ id);
	  }
	  @Test
	  public void methodThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("Class Three : "+ id);
	  }
}
