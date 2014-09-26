/*******************************************************************************
 * File: PathStyleConfiguration.java
 * 
 * Date: 2014/08/10
 * Author: Mikhail Niedre
 * 
 * Copyright (c) 2014 Original authors and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * <a href="http://www.eclipse.org/legal/epl-v10.html">epl-v1.0</a>
 *
 * Contributors:
 * Mikhail Niedre - initial API and implementation
 *******************************************************************************/
package cane.brothers.e4.commander.pathTable.config;

import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultDisplayConverter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.TextPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.LineBorderDecorator;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * TODO
 *
 */
public class PathStyleConfiguration extends AbstractRegistryConfiguration {

    public Color bgColor = GUIHelper.COLOR_WHITE;
    public Color fgColor = GUIHelper.COLOR_BLACK;
    public Color gradientBgColor = GUIHelper.COLOR_WHITE;
    public Color gradientFgColor = GUIHelper.getColor(136, 212, 215);
    public Font font = GUIHelper.DEFAULT_FONT;
    public HorizontalAlignmentEnum hAlign = HorizontalAlignmentEnum.LEFT;
    public VerticalAlignmentEnum vAlign = VerticalAlignmentEnum.TOP;
    public BorderStyle borderStyle = null;

    public ICellPainter cellPainter = new LineBorderDecorator(new TextPainter());

    @Override
    public void configureRegistry(IConfigRegistry configRegistry) {
	configRegistry.registerConfigAttribute(
		CellConfigAttributes.CELL_PAINTER, cellPainter);

	Style cellStyle = new Style();
	cellStyle.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR,
		bgColor);
	cellStyle.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR,
		fgColor);
	cellStyle.setAttributeValue(
		CellStyleAttributes.GRADIENT_BACKGROUND_COLOR, gradientBgColor);
	cellStyle.setAttributeValue(
		CellStyleAttributes.GRADIENT_FOREGROUND_COLOR, gradientFgColor);
	cellStyle.setAttributeValue(CellStyleAttributes.FONT, font);
	cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT,
		hAlign);
	cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT,
		vAlign);
	cellStyle.setAttributeValue(CellStyleAttributes.BORDER_STYLE,
		borderStyle);

	configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE,
		cellStyle);

	configRegistry.registerConfigAttribute(
		CellConfigAttributes.DISPLAY_CONVERTER,
		new DefaultDisplayConverter());
    }

}
