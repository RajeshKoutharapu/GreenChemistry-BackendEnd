package com.Greenness.GreenApp.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Greenness.GreenApp.Service.Tab1Service;
import com.Greenness.GreenApp.Service.finalReportService;
import com.Greenness.GreenApp.Service.tab4Service;
import com.Greenness.GreenApp.Service.Tab2Services.tab2Service;
import com.Greenness.GreenApp.Service.Tab3Services.tab3Service;
import com.Greenness.GreenApp.model.tabFourDataClass;
import com.Greenness.GreenApp.model.tabOneDataClass;
import com.Greenness.GreenApp.model.tabThreeDataClass;
import com.Greenness.GreenApp.model.tabTwoDataClass;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://greeitam.vercel.app")
public class MainController {
	
	@Autowired
	Tab1Service tab1service;
	@Autowired
	tab2Service tab2service;
	@Autowired
	tab3Service tab3Service;
	@Autowired
	tab4Service tab4service;
	@Autowired
	finalReportService finalreportservice;


	 @PostMapping("/tab1-data")
	public void tab1(@RequestBody tabOneDataClass tabonedata) {

        tab1service.Tab1DataService(tabonedata);
        System.out.println("TAB one Data recived");
		
	}
	 @PostMapping("/tab2-data")
	 public ResponseEntity<String> tab2(@RequestBody tabTwoDataClass tabtwodata) {
	     try {
	         tab2service.tab2DateService(tabtwodata);
	         return ResponseEntity.ok("Tab2 data processed successfully");
	     } catch (Exception e) {
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body("Error processing Tab2 data: " + e.getMessage());
	     }
	 }

	 @PostMapping("/tab3-data")
	 public void tab3(@RequestBody tabThreeDataClass tabthreedata) {
		
		try {
		
		tab3Service.getTab3Services(tabthreedata);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	 }
	 
	 @PostMapping("/tab4-data")
	 public ResponseEntity <Map<String,String>>tab4(@RequestBody tabFourDataClass tabfourdata) {
		 tab4service.getTab4Data(tabfourdata);
		 return new  ResponseEntity<Map<String,String>>(finalreportservice.getResponseData(),HttpStatus.OK);
	 }
}
