package org.puppetory.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class CollectorsOverviewController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/collectors")
    public String showCollectors(Model model){

        Object collectorsScheduler = applicationContext.getBean("collectorsScheduler");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        String out = collectorsScheduler.getClass().getName();
        out += "<br />---<br />";

        Map<String,ScheduledMethodRunnable> beans = applicationContext.getBeansOfType(ScheduledMethodRunnable.class);

        for(String beanName : beans.keySet()){
            ScheduledMethodRunnable scheduledMethodRunnable = beans.get(beanName);
            Object target = scheduledMethodRunnable.getTarget();

            out += target.getClass().getName();
        }

        out += "<br />---<br />";

        for(String beanName : beanDefinitionNames){
            out += beanName + "<br />";
        }

        model.addAttribute("report", out);

        return "webReport";
    }
}
