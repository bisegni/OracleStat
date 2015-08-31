/**
 * 
 */
package com.example.oraclestat.fill;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.vaadin.data.Item;
import com.vaadin.ui.Table;

/**
 * @author bisegni
 *
 */
public class TableFiller {

	@SuppressWarnings("unchecked")
	static public void tableFiller(Table tableToFill, ResultSet resultSet) throws SQLException {
		//get all column header that will be used as column name for query result set
		String[] columnHeaders = tableToFill.getColumnHeaders();
		//clean table
		for (Object id : tableToFill.getContainerPropertyIds()) {
			
			//fetch table item
			Item tableRow = tableToFill.getItem(id);
			
			//fill row 
			for (String string : columnHeaders) {
				tableRow.getItemProperty(string).setValue(resultSet.getObject(string));
			}
		} 
	}
}
