/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navin;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author sofiene
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jmsDemo/navinDest")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MyBean implements MessageListener {
    
    public MyBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        TextMessage msg = (TextMessage) message ;
        try {
            System.out.println("Mr."+msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
