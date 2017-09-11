package com.dot.jms;

import java.util.Date;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dot.pojo.TbSensorData;
import com.dot.pojo.msgSensorPacket;
import com.dot.service.SensorCatService;
import com.dot.service.SensorDataService;
import com.dot.utils.JsonUtils;



@Controller
public class sensorDataListener implements  MessageListener{
	
	private static final Logger LOG = Logger.getLogger(ReceiveMessageListener.class); 
	@Autowired
	private SensorDataService sernsorDataService;	
	@Autowired
	private SensorCatService sernsorCatService;
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if(message instanceof BytesMessage ){
			LOG.info("BytesMessage incoming" );
			BytesMessage bm= (BytesMessage) message;
			
            byte[] b = new byte[1024];
            
            int len = -1;    
            String sm;
              
            try {
            	len=bm.readBytes(b);
            	if(len != -1){
            		sm = new String(b, 0, len);
            	//	LOG.info(sm);  
            		msgSensorPacket sensorPacket = new msgSensorPacket();
            		sensorPacket = (msgSensorPacket) JsonUtils.jsonString2Object(sm,msgSensorPacket.class);
            	//	System.out.println(sensorPacket.toString());
            		// insert the data record;
            		
            		TbSensorData sd = new TbSensorData();
            		sd.setMac(sensorPacket.getMac());
            		
            		Date dd= new Date();
            		long diff = dd.getTime()/1000;
             		sd.setTimestamp(diff);
             		sd.setDate(dd);
             		
             		long catid;
             		catid = sernsorCatService.getSensorCatIdByName("temperature");
            		sd.setTypeId(catid);
            		sd.setValue(sensorPacket.getTemperature());
               		sernsorDataService.insertSensorData(sd);
               		
             		catid = sernsorCatService.getSensorCatIdByName("humidity");
            		sd.setTypeId(catid);
            		sd.setValue(sensorPacket.getHumidity());
               		sernsorDataService.insertSensorData(sd);            		
               		
             		catid = sernsorCatService.getSensorCatIdByName("ir");
            		sd.setTypeId(catid);            		
            		sd.setValue(sensorPacket.getIr());
               		sernsorDataService.insertSensorData(sd);   		

             		catid = sernsorCatService.getSensorCatIdByName("pressure");
            		sd.setTypeId(catid);  
            		sd.setValue((float)sensorPacket.getPressure());
               		sernsorDataService.insertSensorData(sd);   		

             		catid = sernsorCatService.getSensorCatIdByName("light");
            		sd.setTypeId(catid);                 		
            		sd.setValue(sensorPacket.getLight());
               		sernsorDataService.insertSensorData(sd);               		

             		catid = sernsorCatService.getSensorCatIdByName("noise");
            		sd.setTypeId(catid);               		
            		sd.setValue((float)sensorPacket.getNoise());
               		sernsorDataService.insertSensorData(sd);   		

             		catid = sernsorCatService.getSensorCatIdByName("battery");
            		sd.setTypeId(catid);                   		
            		sd.setValue((float)sensorPacket.getBattery());
               		sernsorDataService.insertSensorData(sd);  

             		catid = sernsorCatService.getSensorCatIdByName("accelerate");
            		sd.setTypeId(catid);               		
            		sd.setValue(sensorPacket.getAccelerate_X());
            		sd.setValue1(sensorPacket.getAccelerate_Y()); 
            		sd.setValue2(sensorPacket.getAccelerate_Z());               		
               		sernsorDataService.insertSensorData(sd);    

             		catid = sernsorCatService.getSensorCatIdByName("gyroscope");
            		sd.setTypeId(catid); 
            		sd.setValue(sensorPacket.getGyroscope_X());
            		sd.setValue1(sensorPacket.getGyroscope_Y()); 
            		sd.setValue2(sensorPacket.getGyroscope_Z());               		
               		sernsorDataService.insertSensorData(sd);  
               		
             		catid = sernsorCatService.getSensorCatIdByName("hall");            		
            		sd.setTypeId(catid);             		
            		sd.setValue((float)sensorPacket.getHall());
               		sernsorDataService.insertSensorData(sd);      
               		           		
            	}
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				System.out.println("reason "+e.getErrorCode());
				System.out.println("msg "+e.getMessage());
				System.out.println("loc "+e.getLocalizedMessage());
				System.out.println("cause "+e.getCause());
				System.out.println("excep "+e);				
				e.printStackTrace();
			}   			
			
		}
        else{
        	System.out.println("not a text message!");
        }
	}
}
