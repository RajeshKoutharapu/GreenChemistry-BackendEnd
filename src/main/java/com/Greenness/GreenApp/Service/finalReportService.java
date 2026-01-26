package com.Greenness.GreenApp.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import config.SpaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Greenness.GreenApp.Service.Tab2Services.tab2Service;
import com.Greenness.GreenApp.Service.Tab3Services.tab3Service;
import com.Greenness.GreenApp.model.NumberOfGases;
import com.Greenness.GreenApp.model.tabThreeDataClass;


@Service
public class finalReportService {

   
	@Autowired
	tab4Service tab4service;
    @Autowired
    tab2Service tab2service;
    @Autowired 
    Tab1Service tab1service;
    @Autowired
    preReportResultsService prereportresult;
//    @Autowired
//    NumberOfGases gases;
    
    @Autowired
    tab3Service tab3service;
    
    StringBuffer gassesNames; 
    StringBuffer chemicalNames;
    StringBuffer generalIntrumentsNames;
    StringBuffer mainInstrumentsNames;
   
	Map<String,String> finalreportmwmbersmap=new HashMap<>();

  
	
	public Map<String,String> getResponseData(){
		 //making it null because of the duplication whne ever you hit api again it is appending to the existing data
		 chemicalNames=new StringBuffer();
		 gassesNames=new StringBuffer();
    	 generalIntrumentsNames=new StringBuffer();
    	 mainInstrumentsNames=new StringBuffer();;


		//getting all the names of intruments,gasses and chemicals
		 for(int i=0;i<tab3service.getThabThreeData().getNumberOfChemicals().getNfpaFlammabilityValue().size();i++) {
			
	    	 System.out.println(tab3service.getThabThreeData().getNumberOfChemicals().getNfpaFlammabilityValue().get(i).get(0));
	    	 chemicalNames.append(tab3service.getThabThreeData().getNumberOfChemicals().getNfpaFlammabilityValue().get(i).get(0)+", ");
	     }
	     
	     for(int i=0;i<tab3service.getThabThreeData().getNumberOfGases().getNfpaFlammabilityValue().size();i++) {

	    	
	    	 System.out.println(tab3service.getThabThreeData().getNumberOfGases().getNfpaFlammabilityValue().get(i).get(0));
	    	 gassesNames.append(tab3service.getThabThreeData().getNumberOfGases().getNfpaFlammabilityValue().get(i).get(0)+", ");
	     }
	     
	     for(int i=0;i<tab2service.getTab2Data().getGeneralInstruments().size();i++) {

	    	 
	    	 System.out.println(tab2service.getTab2Data().getGeneralInstruments().get(i).getInstrument());
	    	 generalIntrumentsNames.append(tab2service.getTab2Data().getGeneralInstruments().get(i).getInstrument()+", ");
	     }
	     
	     for(int i=0;i<tab2service.getTab2Data().getMainInstruments().size();i++) {

	    	 System.out.println(tab2service.getTab2Data().getMainInstruments().get(i).getInstrumentName());
	    	 mainInstrumentsNames.append(tab2service.getTab2Data().getMainInstruments().get(i).getInstrumentName()+", ");
	     }
		
     finalreportmwmbersmap.put("numberAnalytesStudied",String.valueOf(tab4service.numberofanalytes));
     finalreportmwmbersmap.put("totalEnergyConsumedInKwh","Corrected Energy = "+String.valueOf(tab2service.getEnergyConsumptionFinalREsult())+"  ( Actual Energy = "+String.valueOf(tab2service.getRawEnergy())+")");
     finalreportmwmbersmap.put("totalWasteGenerated","Corrected Waste = "+String.valueOf(tab4service.totalwastegenerated)+"  ( Actual Waste = "+String.valueOf(tab4service.getRawwateGenerated())+")");
     finalreportmwmbersmap.put("numberSolutionsPrepared",String.valueOf(tab1service.getNumberofSolutionsPrepared()));
     finalreportmwmbersmap.put("numberOfGeneralInstrumentsUsed",String.valueOf(tab2service.getNumberOfGeneralInstruments()));
     finalreportmwmbersmap.put("numberOfMainInstrumentsUsed",String.valueOf(tab2service.getNumberOfMainInstruments()));
     finalreportmwmbersmap.put("instrumentPosition",tab4service.getinstrumentposition());
    
     finalreportmwmbersmap.put("samplePreparationInformation",tab4service.getSamplepreperationinfo());
     finalreportmwmbersmap.put("derivatizationInformation",tab4service.getDerivatizationInformation());
     finalreportmwmbersmap.put("wasteManagement",tab4service.getwastemanagementForFinalReport());
     finalreportmwmbersmap.put("wastemanagementInformation",tab4service.getwasteManagementInfo());
     finalreportmwmbersmap.put("OperatorSafetyFinalResultForGraph",String.valueOf( prereportresult.operationsaftyfinalresult));
     finalreportmwmbersmap.put("InstrumentPositionForGraph",String.valueOf(prereportresult.instrumentposition));
     finalreportmwmbersmap.put("SamplePreparationForGraph",String.valueOf(prereportresult.samplepreparation));
     finalreportmwmbersmap.put("HazardusChemicalResltForGraph",String.valueOf(prereportresult.hazarduschemicalresult));
     finalreportmwmbersmap.put("DerivatizationForGraph",String.valueOf(prereportresult.devrivation));
     finalreportmwmbersmap.put("MiniautorisationResultForGraph",String.valueOf(prereportresult.miniaothorizationresult));
     finalreportmwmbersmap.put("WasteGenerationForGraph",String.valueOf(prereportresult.wastegeneration));
     finalreportmwmbersmap.put("EnergyConsumptionFinalResultForGraph",String.valueOf(prereportresult.energyconsumtionfinalresult));
     finalreportmwmbersmap.put("AnalysingTheMultipleAnalytesInASingleRunForGraph",String.valueOf(prereportresult.analysingthemultipleanalytesinasinglerun));
     finalreportmwmbersmap.put("GenaralIntrumentNames",(generalIntrumentsNames.length()>0)?generalIntrumentsNames.toString().substring(0,generalIntrumentsNames.length()-2):"No Instrument Selected");
     finalreportmwmbersmap.put("MainInstrumentsNames",(mainInstrumentsNames.length()>0)?mainInstrumentsNames.toString().substring(0,mainInstrumentsNames.length()-2):"No Instrument Selected");
     finalreportmwmbersmap.put("GassesNames",(gassesNames.length()>0)?gassesNames.toString().substring(0,gassesNames.length()-2):"No Gasess Selected");
     finalreportmwmbersmap.put("ChemicalNames",(chemicalNames.length()>0)?chemicalNames.toString().substring(0,chemicalNames.length()-2):"No Chemical Selected");
     
     //caliculating the Greenness of the dtata 
     
     Double greenness=(prereportresult.operationsaftyfinalresult+prereportresult.instrumentposition+prereportresult.samplepreparation+prereportresult.hazarduschemicalresult+prereportresult.devrivation+prereportresult.miniaothorizationresult+prereportresult.wastegeneration+prereportresult.energyconsumtionfinalresult+prereportresult.analysingthemultipleanalytesinasinglerun)/9;
       greenness=Math.round(greenness*10.0)/10.0;//rounding of the decimal points 
     finalreportmwmbersmap.put("resultsGreenness",String.valueOf(greenness));
     //caliculating the result 
     if(greenness>=60)
    	 finalreportmwmbersmap.put("finalResult","Green");
     else if(greenness>=50 && greenness<=59.9)
    	 finalreportmwmbersmap.put("finalResult","moderately"+ " Green");
     else
    	 finalreportmwmbersmap.put("finalResult","Non Green");

        
     System.out.println(finalreportmwmbersmap);
     
    // System.out.println(tab3service.getThabThreeData().getNumberOfGases().getNfpaFlammabilityValue().get(0).get(0)+"        "+tab3service.getThabThreeData().getNumberOfGases().getNfpaHealthValue().get(0).get(0));
//	for(  String name:tab3service.getThabThreeData().getNumberOfGases().getNfpaFlammabilityValue().get(0) ) {
//		System.out.println(name);
//	}
    
     return finalreportmwmbersmap;
	}
}
