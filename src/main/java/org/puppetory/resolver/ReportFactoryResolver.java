package org.puppetory.resolver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.puppetory.factories.ReportFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class ReportFactoryResolver implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Log logger;

    public ReportFactoryResolver() {
        logger = LogFactory.getLog(ReportFactoryResolver.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * resolve ReportFactory by the given report id
     *
     * @param reportId
     * @return report if found or null
     */
    public ReportFactory resolveReportFactory(String reportId){
        ReportFactory reportFactory = null;

        if(reportId != null && reportId.isEmpty() == false){
            try {
                reportFactory = (ReportFactory) applicationContext.getBean(reportId);
            } catch (BeansException e) {
                logger.warn("unable to resolve report factory by id: '" + reportId + "'");
            }
        }

        return reportFactory;
    }
}
