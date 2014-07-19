package cane.brothers.nattable.test.person.grid.layer;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;

public class RowHeaderLayerStack extends AbstractLayerTransform {

	/**
	 * Constructor
	 * 
	 * @param dataProvider
	 * @param bodyLayer
	 */
	public RowHeaderLayerStack(IDataProvider dataProvider, BodyLayerStack bodyLayer) {
		DataLayer dataLayer = new DataLayer(dataProvider, 50, 20);
		RowHeaderLayer rowHeaderLayer = new RowHeaderLayer(dataLayer, bodyLayer, bodyLayer.getSelectionLayer());
		setUnderlyingLayer(rowHeaderLayer);
	}
}
