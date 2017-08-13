package com.dot.jms;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dot.pojo.TbGatewayInfo;
import com.dot.service.GatewayInfoService;
import com.dot.utils.JsonUtils;



@Controller
public class gatewayReportListener implements  MessageListener {
	

	
	private static final Logger LOG = Logger.getLogger(ReceiveMessageListener.class); 
	@Autowired
	private GatewayInfoService gatewayInfoService;	
	

	@Override
	public void onMessage(Message message) {
	
		
		if (message instanceof TextMessage) {  
            TextMessage text = (TextMessage) message;  
            try {          
	                LOG.info("Received message:" + text.getText());    
		            TbGatewayInfo item;
		            item = (TbGatewayInfo) JsonUtils.jsonString2Object(text.getText(), TbGatewayInfo.class);
		            gatewayInfoService.updateRunGatewayInfo(item);

		            

            } catch (JMSException e) {  
                e.printStackTrace();  
            }  
        }  
		
	}
	

	  
}