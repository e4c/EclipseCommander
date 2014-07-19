package cane.brothers.nattable.test.person.grid;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultRowHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.CornerLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;

import cane.brothers.nattable.test.person.data.Person;
import cane.brothers.nattable.test.person.data.PersonColumnHeaderDataProvider;
import cane.brothers.nattable.test.person.data.PersonColumnPropertyAccessor;
import cane.brothers.nattable.test.person.grid.layer.BodyLayerStack;
import cane.brothers.nattable.test.person.grid.layer.ColumnHeaderLayerStack;
import cane.brothers.nattable.test.person.grid.layer.RowHeaderLayerStack;


public class PersonGridLayer extends GridLayer {
	
	private final List<Person> people = Arrays.asList(
			new Person(100, "Mickey Mouse", new Date(1000000)), 
			new Person(110, "Batman", new Date(2000000)), 
			new Person(120, "Bender", new Date(3000000)), 
			new Person(130, "Cartman", new Date(4000000)), 
			new Person(140, "Dogbert", new Date(5000000)));
	
	private final Map<String, String> propertyToLabels = new HashMap<String,String>();
	
    private final ListDataProvider<Person> bodyDataProvider;
    
    private IColumnPropertyAccessor<Person> columnPropertyAccessor;
    
	private BodyLayerStack bodyLayer;
	
	public PersonGridLayer() {
		super(true);
		
		propertyToLabels.put("id", "ID");
		propertyToLabels.put("name", "First Name");
		propertyToLabels.put("birthDate", "DOB");
		
		columnPropertyAccessor = new PersonColumnPropertyAccessor(propertyToLabels);
		bodyDataProvider = new ListDataProvider<Person>(people, columnPropertyAccessor);
		
		PersonColumnHeaderDataProvider colHeaderDataProvider = new PersonColumnHeaderDataProvider(propertyToLabels);
		DefaultRowHeaderDataProvider rowHeaderDataProvider = new DefaultRowHeaderDataProvider(bodyDataProvider);
		
		bodyLayer = new BodyLayerStack(bodyDataProvider);
		ColumnHeaderLayerStack columnHeaderLayer = new ColumnHeaderLayerStack(colHeaderDataProvider, bodyLayer);
		RowHeaderLayerStack rowHeaderLayer = new RowHeaderLayerStack(rowHeaderDataProvider, bodyLayer);
		
		DefaultCornerDataProvider cornerDataProvider = new DefaultCornerDataProvider(colHeaderDataProvider, rowHeaderDataProvider);
		CornerLayer cornerLayer = new CornerLayer(new DataLayer(cornerDataProvider), rowHeaderLayer, columnHeaderLayer);

		setBodyLayer(bodyLayer);
		setColumnHeaderLayer(columnHeaderLayer);
		setRowHeaderLayer(rowHeaderLayer);
		setCornerLayer(cornerLayer);
		
		// init(true);
	}
}
