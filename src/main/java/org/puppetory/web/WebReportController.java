package org.puppetory.web;

import org.bson.Document;
import org.puppetory.factories.ReportFactory;
import org.puppetory.model.api.Report;
import org.puppetory.model.impl.FilterImpl;
import org.puppetory.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebReportController implements ApplicationContextAware{

	private ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;		
	}
	
	@RequestMapping("/report/{reportId}")
	public String showReport(@PathVariable String reportId,
                             @RequestParam(required = false) String searchFilter,
                             Model model) throws Exception {

		try {
            ReportFactory reportFactory = (ReportFactory) applicationContext.getBean(reportId);
            Report report;
            if(searchFilter == null || searchFilter.isEmpty()){
                report = reportFactory.createReport();
            }
            else if(validateFilter(searchFilter)){
                report = reportFactory.createReport(new FilterImpl(searchFilter));
            } else {
                report = reportFactory.createReport();
                model.addAttribute("searchMsg", "Invalid search query: '" + searchFilter + "'. <br />" +
                                "Try to use a format like: '{name: \"searchquery\"}'");
            }

			model.addAttribute("searchFilter", searchFilter);
			model.addAttribute("report", report.toString());
			model.addAttribute("reportId", reportId);
			model.addAttribute("reportUrl", "/report/" + reportId);
		} catch (BeansException e) {
            e.printStackTrace();
			throw new ResourceNotFoundException();
		}
				
		return "webReport";
	}

    private boolean validateFilter(String searchFilter){

        if(searchFilter == null || searchFilter.isEmpty()){
            return false;
        }

        Document document = null;

        try{
            document = Document.parse(searchFilter);
        } catch (Exception e){
            return false;
        }

        return document == null ? false : true;
    }

	
}
