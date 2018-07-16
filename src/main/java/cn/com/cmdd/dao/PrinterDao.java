package cn.com.cmdd.dao;


import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.Printer;
import cn.com.cmdd.domain.Product;

import java.util.List;

public interface PrinterDao {
	void savePrinter(Printer Printer);
	void insertById(Printer Printer);
	void deletePrinter(@Param("id") Long id);
	void updatePrinter(Printer Printer);
	List<Printer> getPrinter(
            @Param("shop_id") Integer shop_id,
            @Param("id") Long id
    );
	Printer getPrinterNameByPrinter_type(
            @Param("shop_id") Integer shop_id,
            @Param("printer_type") Long printer_type);
	
	//同步数据
		List<Printer> selectByIsUpload(Integer shop_id, Integer sysn_status);
		int updateIsUpload(Long id);
}
