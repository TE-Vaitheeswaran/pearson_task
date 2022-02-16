package com.te.pearson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.te.pearson.beans.Response;
import com.te.pearson.beans.Store;
import com.te.pearson.service.ServiceInterface;

@RestController
@RequestMapping("/data")
public class StoreController {

	@Autowired
	private ServiceInterface service;

	@GetMapping("/fetch/{storeId}")
	public ResponseEntity<Response> getStore(@PathVariable String storeId) {
		Response res = new Response(false, service.getStore(storeId));
		return new ResponseEntity<Response>(res, HttpStatus.OK);

	}

	@GetMapping("/fetchbycity")
	public ResponseEntity<Response> getStoreInfo(@RequestParam String select) {
		Response res2 = new Response(false, service.getStoreInfo(select));
		return new ResponseEntity<Response>(res2, HttpStatus.OK);
	}

}
