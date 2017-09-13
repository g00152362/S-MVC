package com.dot.service;

import java.util.List;

import com.dot.pojo.TbSensorCat;


public interface SensorCatService {
	List<TbSensorCat> getTbSensorCatList(long parentid);
	TbSensorCat getSensorCatItemByName(String catName);
	long getSensorCatIdByName(String catName);
}
