package org.puppetory.web;

import org.puppetory.model.api.Report;
import org.puppetory.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebReportController implements ApplicationContextAware{

	private ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;		
	}
	
	@RequestMapping("/report/{reportId}")
	public String showReport(@PathVariable String reportId, Model model) throws ResourceNotFoundException{
		
		try {
			Report report = (Report) applicationContext.getBean(reportId);
			model.addAttribute("report", report.toString());
		} catch (BeansException e) {
			throw new ResourceNotFoundException();
		}
				
		return "index";		
	}

	
}
