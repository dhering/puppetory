package org.puppetory.web;

import org.bson.Document;
import org.puppetory.factories.ReportFactory;
import org.puppetory.model.api.Report;
import org.puppetory.model.impl.FilterImpl;
import org.puppetory.resolver.ReportFactoryResolver;
import org.puppetory.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * web report controller to show a web report
 */
@Controller
public class WebReportController{
    private ReportFactoryResolver reportFactoryResolver;

    @Autowired
    public WebReportController(ReportFactoryResolver reportFactoryResolver) {
        this.reportFactoryResolver = reportFactoryResolver;
    }

    /**
     * Show a web report by the given report id. Use the given search filter to reduce the results.
     *
     * @param reportId
     * @param searchFilter
     * @param searchFilter
     * @return webReport view name
     * @throws Exception
     */
	@RequestMapping("/report/{reportId}")
	public ModelAndView showReport(@PathVariable String reportId,
                             @RequestParam(required = false) String searchFilter) throws Exception {

        ModelAndView mv = new ModelAndView("webReport");
        ReportFactory reportFactory = reportFactoryResolver.resolveReportFactory(reportId);

        if(reportFactory == null){
            throw new ResourceNotFoundException("unable to resolve report factory by id: '" + reportId + "'");
        }

        Report report;
        if(searchFilter == null || searchFilter.isEmpty()){
            report = reportFactory.createReport();
        }
        else if(validateFilter(searchFilter)){
            report = reportFactory.createReport(new FilterImpl(searchFilter));
        } else {
            report = reportFactory.createReport();
            mv.addObject("searchMsg", "Invalid search query: '" + searchFilter + "'. <br />" +
                            "Try to use a format like: '{name: \"searchquery\"}'");
        }

        mv.addObject("searchFilter", searchFilter);
        mv.addObject("report", report.toString());
        mv.addObject("reportId", reportId);
        mv.addObject("reportUrl", "/report/" + reportId);
				
		return mv;
	}

    /**
     * validate if the given search filter has a valid JSON format
     *
     * @param searchFilter
     * @return true if the filter is valid
     */
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
