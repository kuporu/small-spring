package org.hgc.springframework.test.lazyLoad;

import net.sf.cglib.proxy.Dispatcher;
import org.hgc.springframework.test.proxy.TargetObject;

public class ConcreteClassDispatcher implements Dispatcher {
 
    @Override
    public Object loadObject() throws Exception {
        System.out.println("Dispatcher...");
        PropertyBean propertyBean = new PropertyBean();
        propertyBean.setKey("xxx");
        propertyBean.setValue(new TargetObject());
        return propertyBean;
    }
 
}