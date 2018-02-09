package com.brotherhui.cucumber.common.velocity;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiaohui.c.liu
 *
 */
@Service
public class VelocityWrapper {

	@Autowired
    private VelocityEngine velocityEngine;

    public String wrap(Map<String, Object> params, String templateFileName) {
    	VelocityContext context = new VelocityContext();
        updateContext(context, params);
        Template template = velocityEngine.getTemplate(templateFileName);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    private void updateContext(VelocityContext context, Map<String, Object> params) {
        for (String key : params.keySet()) {
        	context.put(key, params.get(key));
        }
    }
}
