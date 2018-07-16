package cn.com.cmdd.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.cmdd.dao.PrintJobDao;
import cn.com.cmdd.domain.PrintJob;

import java.util.List;

@Service
@Transactional
public class PrintJobService {
	@Autowired
	PrintJobDao printJobDao;
	
	public void savePrintJobList(List<PrintJob> printJobList) {
		printJobDao.savePrintJobList(printJobList);
	}
	
	public void savePrintJob(PrintJob printJob) {
		printJobDao.savePrintJob(printJob);
	}
	
	
	public List<PrintJob> getPrintJobList(Integer shop_id){
		
		List<PrintJob> printJobList = printJobDao.getPrintJobList(shop_id);
						
		/*List<List<PrintJob>> printJobList_end = new ArrayList<List<PrintJob>>();
		
		if(printJobList.size() > 0) {		
			List<PrintJob> printJobList_new = new ArrayList<PrintJob>();		
			
			for (int i = 0; i < printJobList.size(); i++) {
				
				if(i==0||printJobList.get(i).getOrder_no().equals(printJobList.get(i-1).getOrder_no())) {
					printJobList_new.add(printJobList.get(i));
				}else {				
					printJobList_end.add(printJobList_new);
					printJobList_new = new ArrayList<PrintJob>();				
					printJobList_new.add(printJobList.get(i));
				}
				if(i==printJobList.size()-1) {
					printJobList_end.add(printJobList_new);					
				}
				printJobDao.removePrintJobByShopId(printJobList.get(i).getId());	
			}						
		}
		*/		
		return printJobList;						
	}
	
	public void removePrintJob(Long id) {
		printJobDao.removePrintJobById(id);
	}
}
