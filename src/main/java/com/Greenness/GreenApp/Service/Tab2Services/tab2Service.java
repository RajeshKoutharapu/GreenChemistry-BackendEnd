package com.Greenness.GreenApp.Service.Tab2Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Greenness.GreenApp.model.tabTwoDataClass;

@Service
public class tab2Service {
	
	@Autowired
	generalInstrumentsService generalinstrumentsservice;
	@Autowired
	mainInstrumentsService maininstrumentsservice;
	
	Double generalinstrumentsenergy=0D;
	Double maininstrumentsenergy=0D;
	Double efluentwaste=0D;
	 Double energyconsumptionfinalresult=0D;
	 
	 //below both are used used in final report generation
	 Integer numberofgeneralimstruments=0;
	 Integer numberofmaininstruments=0;
	 
	 //variable for rawenegryconsumptio for the result tab
	 Double rawEnegryConsumtion=0D;

	 //this variable store the tab2 data and used at finel page to pass the intruments names to result
	 tabTwoDataClass tab2data;
	public void tab2DateService(tabTwoDataClass tab2dataclass) {
		
		//assigning tab2data to use to pass names to result
		tab2data=tab2dataclass;
		//method call for generalinstrument total energy
		 generalinstrumentsenergy=generalinstrumentsservice.generalInstrumentenEnergy(tab2dataclass.getGeneralInstruments());
		 numberofgeneralimstruments=tab2dataclass.getGeneralInstruments().size();
		System.out.println("General instruments total  :"+generalinstrumentsenergy);
		//method call for mainInstruments total energy
		 maininstrumentsenergy=maininstrumentsservice.getMainInstrumentsenergy(tab2dataclass.getMainInstruments());
		 numberofmaininstruments=tab2dataclass.getMainInstruments().size();
		System.out.println("Main instruments totla :"+maininstrumentsenergy);
		
		//Method call for Efluent waste
		 efluentwaste=maininstrumentsservice.getEfluentWaste(tab2dataclass.getMainInstruments());
		System.out.println("total efluentWaste :"+efluentwaste);
		
	}
	
	//Method for caliculating energy consumption result and  returning final result based on pint number 8 in word document
	public Integer getEnergyConsomptionResult(Integer noofanalytics) {
		
		
		System.out.println("general intrumentenergy"+generalinstrumentsenergy+"  maininstrumentEnergy "+maininstrumentsenergy);
		
		
		rawEnegryConsumtion=Math.round((generalinstrumentsenergy+maininstrumentsenergy)*10.10)/10.0;
      System.out.println("raw :"+rawEnegryConsumtion);
		energyconsumptionfinalresult =Math.round((generalinstrumentsenergy+maininstrumentsenergy)/noofanalytics*10.0)/ 10.0;
      
	  System.out.println("Energy consumption  final result :"+energyconsumptionfinalresult);
	  if(energyconsumptionfinalresult>=2.5)
		   return 0;
	  else if(energyconsumptionfinalresult>=2)
		  return 25;
	  else if(energyconsumptionfinalresult>=1.5)
		   return 50;
	  else if(energyconsumptionfinalresult>=1.0)
		  return 75;
	  else if(energyconsumptionfinalresult>=0.5)
		  return 75;
	 // else if(energyconsumptionfinalresult<=0.49)
	  else
		  return 100;
	  
	}
	
	// method  return the waste generated in tab2 used to caliculate total waste in tab3
	public Double getEfluentWaste() {
		return efluentwaste;
				
	}
	
	//method return energy consumption final result, used in final report data gathering
	public Double getEnergyConsumptionFinalREsult() {
		return energyconsumptionfinalresult;
	}
	
	//these methods are called by the final report class for generating report
	public Integer getNumberOfGeneralInstruments() {
		return numberofgeneralimstruments;
	}
	
	public Integer getNumberOfMainInstruments() {
		return numberofmaininstruments;
	}
	
	public Double getRawEnergy() {
		return rawEnegryConsumtion;
	}
	
	public tabTwoDataClass getTab2Data() {
		return tab2data;
	}
	
}
