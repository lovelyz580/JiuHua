package sun.bz1.entity;


import util.ExcelImport;

/**
 * 导入Excel
 * 
 * 子女教育支出表
 * 
 * 实体类
 */

public class OrderTableImportFile {

	/**
	 * 订单名称
	 */
	@ExcelImport(columnIndex = 0)
    private String write1;

	/**
	 * 订单联系人
	 */
	@ExcelImport(columnIndex = 1)
	private String write2;

	public String getWrite1() {
		return write1;
	}

	public void setWrite1(String write1) {
		this.write1 = write1;
	}

	public String getWrite2() {
		return write2;
	}

	public void setWrite2(String write2) {
		this.write2 = write2;
	}
}
