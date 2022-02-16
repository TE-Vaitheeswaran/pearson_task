package com.te.pearson.service;

import java.util.List;

import com.te.pearson.beans.Store;

public interface ServiceInterface {

	public Object getStore(String storeId);

	public List<Store> getStoreInfo(String select);

}
