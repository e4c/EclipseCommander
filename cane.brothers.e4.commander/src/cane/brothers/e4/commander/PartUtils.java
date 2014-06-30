package cane.brothers.e4.commander;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import cane.brothers.e4.commander.parts.ITabNameId;

public class PartUtils {
	
	/**
	 * Create label for the new tab when You know active tab.
	 * 
	 * @see cane.brothers.e4.commander.parts.ITabNameId.getTabName()
	 * 
	 * @param part active tab. should be not null
	 * @return tab label
	 */
	public static String createPartLabel(MPart part) {
		String label = null;
		if (part.getObject() instanceof ITabNameId) {
			ITabNameId tab = (ITabNameId) part.getObject();
			label = tab.getTabName();
		}
		return label;
	}

	/**
	 * Create id for the new tab.
	 * 
	 * @see cane.brothers.e4.commander.parts.ITabNameId.getElementId()
	 * 
	 * @param part active tab. should be not null.
	 * @return element id for the new tab.
	 */
	public static String createElementId(MPart part) {
		String elemId = null;
		if (part.getObject() instanceof ITabNameId) {
			ITabNameId tab = (ITabNameId) part.getObject();
			elemId = tab.getElementId();
		}
		return elemId;
	}
	
	/**
	 * @param part
	 * @param opposite
	 * @return
	 */
	public static String getPanelId(MPart part, boolean opposite) {
		// panel id's
		String panelId = null;
		
		if (part != null && part.getParent() != null) {
			panelId = part.getParent().getElementId();
			if(opposite) {
				panelId = getOppositePanelId(panelId);
			}
			System.out.println("panel id: " + panelId + "; current: " + !opposite);
		}
		
		return panelId;
	}
	
	/**
	 * @param panelId
	 * @return
	 */
	private static String getOppositePanelId(String panelId) {
		if (panelId != null) {
			return (panelId.equals(IdStorage.LEFT_PANEL_ID) 
					? IdStorage.RIGHT_PANEL_ID
					: IdStorage.LEFT_PANEL_ID);
		}
		return panelId;
	}
}
