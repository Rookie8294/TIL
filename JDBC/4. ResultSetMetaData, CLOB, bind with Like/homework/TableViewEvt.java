package work0817;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class TableViewEvt extends WindowAdapter implements ActionListener{
	
	private TableView view;
	
	public TableViewEvt( TableView view ) {
		this.view = view;
	}//TableViewEvt
	
	@Override
	public void windowClosing(WindowEvent e) {
		view.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == view.getJcbTableName() ) {
			TableStructureInfo();
		}//end if
	}//actionPerformed

	public void TableStructureInfo() {
		
		TableViewDAO tvDAO = TableViewDAO.getInstance();
		
		String tableName = view.getJcbTableName().getSelectedItem().toString();
		
		List<DataVO> list = null;
		
		try {
			list = tvDAO.selectStructureTable(tableName);
			
			//테이블 모든 행 삭제
			view.getDtmTable().setRowCount(0);
			
			String[] rowData = null;
			for( DataVO dVO : list ) {
				rowData = new String[5];
				
				rowData[0] = dVO.getColumnName();
				rowData[1] = dVO.getDataType();
				
				if( dVO.getDataPrecision() != null ) {
					rowData[2] = dVO.getDataPrecision();					
				} else {
					rowData[2] = dVO.getDataLength();
				}
				
				rowData[3] = dVO.getDataDefault();
				rowData[4] = dVO.getNullable();
				
				view.getDtmTable().addRow(rowData);
				
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//TableStructureInfo
	
	
}//class
