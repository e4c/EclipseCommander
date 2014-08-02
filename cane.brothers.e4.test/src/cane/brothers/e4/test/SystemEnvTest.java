/**
 * 
 */
package cane.brothers.e4.test;

import java.util.Map;

/**
 * @author icane
 *
 */
public class SystemEnvTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = System.getenv();

		for(Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
		
		System.out.println(System.getenv().get("HOME"));
	}
}
