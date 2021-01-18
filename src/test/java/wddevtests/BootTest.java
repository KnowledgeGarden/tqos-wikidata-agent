/**
 * 
 */
package wddevtests;

/**
 * @author jackpark
 *
 */
public class BootTest extends TestRoot {

	/**
	 * 
	 */
	public BootTest() {
		System.out.println("A "+environment.getProperties());
		
		environment.shutDown();
		System.exit(0);
	}

}
