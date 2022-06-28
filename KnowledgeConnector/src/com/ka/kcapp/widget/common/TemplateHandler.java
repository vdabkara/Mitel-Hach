package com.ka.kcapp.widget.common;

import com.ka.kcapp.widget.tag.BaseTag;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

import java.io.StringWriter;

import java.util.Map;

import javax.servlet.ServletConfig;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

public class TemplateHandler {
    
    final static Logger logger = Logger.getLogger(TemplateHandler.class);
    
    
    private TemplateHandler() {}
    
    
    public static String getTemplateHtml(Map<String, Object> input, String templateName, ServletContext context) {
        String templateHtml = "";
        try {
            Configuration cfg = new Configuration();
            Template template;
            cfg.setServletContextForTemplateLoading(context, "/widgets/templates/custom");
            cfg.setIncompatibleImprovements(new Version(2, 3, 20));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            if(!templateName.endsWith(".ftl"))
                templateName = templateName.concat(".ftl");
            try{
                template = cfg.getTemplate(templateName);
            }catch(Exception ex){
                logger.debug("Could not find a copy of the file in the custom folder. Proceeding to check in the standard folder");
                cfg.setServletContextForTemplateLoading(context, "/widgets/templates/standard");
                template = cfg.getTemplate(templateName);
            }
            StringWriter output = new StringWriter();
            template.process(input, output);
            templateHtml = output.toString();
        } catch (Exception ex) {
            logger.error("Error in TemplateHandler.getTemplateHtml: ", ex);
        }
        return templateHtml;
    }
    
    
}
