package dot.com.common.pojo;

import java.util.List;

public class EUDataGridResult {
	private Integer  total;
	private List<?> rows;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer  total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> row) {
		this.rows = row;
	}

}
