package com.te.pearson.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.te.pearson.beans.Store;

@Service
public class ServiceImpl implements ServiceInterface {

	@Override
	public Object getStore(String storeId) {
		List<Store> lists = new ArrayList<Store>();

		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("Store Id", "storeid");
		mapping.put("Post Code", "postcode");
		mapping.put("City", "city");
		mapping.put("Address", "address");
		mapping.put("Opened Date", "openeddate");

		HeaderColumnNameTranslateMappingStrategy<Store> strategy = new HeaderColumnNameTranslateMappingStrategy<Store>();
		strategy.setType(Store.class);
		strategy.setColumnMapping(mapping);

		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader("src/main/webapp/WEB-INF/views/stores.csv"));
		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CsvToBean csvToBean = new CsvToBean();

		List<Store> list = csvToBean.parse(strategy, csvReader);
		for (Store str : list) {

			Date dat = null;
			try {
				dat = new SimpleDateFormat("dd/MM/yyyy").parse(str.getOpenedDate());
				Date ref = new Date();

				long l1 = Math.abs(ref.getTime() - dat.getTime());
				long l2 = TimeUnit.DAYS.convert(l1, TimeUnit.MILLISECONDS);
				str.setNumberofdays(l2);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getStoreId().equalsIgnoreCase(storeId)) {
				lists.add(list.get(i));
				return lists;
			}

		}
		return lists;
	}

	@Override
	public List<Store> getStoreInfo(String select) {
		Map<String, String> mapping = new HashMap<String, String>();
		mapping.put("Store Id", "storeid");
		mapping.put("Post Code", "postcode");
		mapping.put("City", "city");
		mapping.put("Address", "address");
		mapping.put("Opened Date", "openeddate");

		HeaderColumnNameTranslateMappingStrategy<Store> strategy = new HeaderColumnNameTranslateMappingStrategy<Store>();
		strategy.setType(Store.class);
		strategy.setColumnMapping(mapping);

		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new FileReader("src/main/webapp/WEB-INF/views/stores.csv"));
		} catch (FileNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CsvToBean csvToBean = new CsvToBean();

		List<Store> list = csvToBean.parse(strategy, csvReader);

		for (Store str : list) {

			Date dat = null;
			try {
				dat = new SimpleDateFormat("dd/MM/yyyy").parse(str.getOpenedDate());
				Date ref = new Date();

				long l1 = Math.abs(ref.getTime() - dat.getTime());
				long l2 = TimeUnit.DAYS.convert(l1, TimeUnit.MILLISECONDS);
				str.setNumberofdays(l2);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (select.equalsIgnoreCase("city")) {
			list = list.stream().sorted((i, j) -> i.getCity().compareTo(j.getCity())).collect(Collectors.toList());
			return list;

		} else if (select.equalsIgnoreCase("opened date"))
			list = list.stream().sorted((i, j) -> i.getOpenedDate().compareTo(j.getOpenedDate()))
					.collect(Collectors.toList());
		return list;

	}

}
