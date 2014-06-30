package cane.brothers.e4.commander.parts;

/**
 * Defines tab name convention interface.
 * 
 * @author cane
 *
 */
public interface ITabNameId {
	
	public static final String ELEMENT_ID = "cane.brothers.e4.commander.part".intern();
	
	/**
	 * @return element id
	 */
	public String getElementId();

	/**
	 * tab name with new id specified for left or right side
	 * 
	 * @return tab name with new id
	 */
	public String getTabName();
}
