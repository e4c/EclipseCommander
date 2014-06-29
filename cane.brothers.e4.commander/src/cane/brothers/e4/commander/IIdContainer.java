/**
 * 
 */
package cane.brothers.e4.commander;

/**
 * All id's contains in this file available as well in the model.
 * 
 * @author cane
 *
 */
public interface IIdContainer {
	

	/** part sash container id */
	//public static final String PART_SASH_CONTAINER_ID = "cane.brothers.e4.commander.partsashcontainer.main"
	//		.intern();
	

	/** part descriptor id  */
	public static final String DYNAMIC_PART_DESCRIPTOR_ID = "cane.brothers.e4.commander.partdescriptor.dynamic"
			.intern();
	
	/** panel id's */
	public static String LEFT_PANEL_ID = "cane.brothers.e4.commander.partstack.left".intern();
	
	public static String RIGHT_PANEL_ID = "cane.brothers.e4.commander.partstack.right".intern();
}
