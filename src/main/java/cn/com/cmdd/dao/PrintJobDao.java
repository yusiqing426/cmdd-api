package cn.com.cmdd.dao;


import org.apache.ibatis.annotations.Param;

import cn.com.cmdd.domain.PrintJob;

import java.util.List;

public interface PrintJobDao {
	void savePrintJobList(@Param("printJobList") List<PrintJob> printJobList);
	void savePrintJob(PrintJob printJob);
	List<PrintJob> getPrintJobList(@Param("shop_id") Integer shop_id);
	void removePrintJobById(@Param("id") Long id);
}



