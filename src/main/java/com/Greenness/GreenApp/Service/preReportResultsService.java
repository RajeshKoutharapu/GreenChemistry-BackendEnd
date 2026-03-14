package com.Greenness.GreenApp.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class preReportResultsService {

	@Autowired
	Tab1Service tab1service;
	
	Integer operationsaftyfinalresult=0;
	Integer instrumentposition=0;
	Integer samplepreparation=0;
	Integer hazarduschemicalresult=0;
	Integer devrivation=0;
	Integer energyconsumtionfinalresult=0;
	Double miniaothorizationresult=0D;
	Integer wastegeneration=0;
	Integer analysingthemultipleanalytesinasinglerun=0; 
	
	public preReportResultsService() {
	
	}
	
	//method that caliculate teh operation safty final result
	public void getOperationSaftyFinalResult(Double chemicalphysicalhazardaverage,Double gasesphysicalhazardaverage) {
		if(chemicalphysicalhazardaverage == -1&&gasesphysicalhazardaverage == -1) {
			 operationsaftyfinalresult=100;
			 return;
		}
		else if(chemicalphysicalhazardaverage==-1) {
			operationsaftyfinalresult= (int) Math.round(gasesphysicalhazardaverage);
		}
		else if(gasesphysicalhazardaverage==-1) {
			operationsaftyfinalresult= (int) Math.round(chemicalphysicalhazardaverage);

		}else {
		operationsaftyfinalresult=(int) Math.round( ((chemicalphysicalhazardaverage+gasesphysicalhazardaverage)/2));
		System.out.println("operationsaftyfinalresult :"+operationsaftyfinalresult);
		}
	}
	
	//method used to set the instrument position based  value from tab4
	public void setInstrumentPosition(Integer instrumentpos) {
		
		 instrumentposition=instrumentpos;
		 System.out.println("instrumentposition :"+instrumentposition);
	}
	//method used to set sample preperation based on filteration average from tab1 and  samplepreperation  from tab4
	public void setSamplePreparation(Integer Sample) {
	
		//getting filteration average for caliculating sample preparation
		Double temp=tab1service.tab1calcvalues.get("filtrationavarage");
		samplepreparation=(int) Math.round(((temp+Sample)/2));
		System.out.println("samplepreparation :"+samplepreparation);
		
	}
	
	public void setHazardusChemicalResult(Integer chealthavg,Integer cflemmabilityavg, Integer ghealthavg,Integer gflemmabilityavg) {
		
		System.out.println(chealthavg+"   "+cflemmabilityavg+"   "+ghealthavg+"   "+gflemmabilityavg);
		
		
		if(gflemmabilityavg!=-1 && chealthavg!=-1){
			hazarduschemicalresult=Math.round( ((chealthavg+cflemmabilityavg)/2-((ghealthavg+gflemmabilityavg)/2)/((chealthavg+cflemmabilityavg)/2)));
			  System.out.println("hazarduschemicalresult :"+hazarduschemicalresult);
		}
		else if(gflemmabilityavg==-1 && chealthavg!=-1) {
			 hazarduschemicalresult=Math.round( (chealthavg+cflemmabilityavg)/2);
			  System.out.println("hazarduschemicalresult :"+hazarduschemicalresult);
			}
			else if(chealthavg==-1 && gflemmabilityavg!=-1) {
				hazarduschemicalresult=Math.round( (ghealthavg+gflemmabilityavg)/2);
				  System.out.println("hazarduschemicalresult :"+hazarduschemicalresult);
			}
		else
		{
			hazarduschemicalresult=100;
			  System.out.println("hazarduschemicalresult :"+hazarduschemicalresult);
		}
		
	}
	
	public void setDerivation(Integer derivationvalue) {
		devrivation=derivationvalue;
		System.out.println("devrivation :"+devrivation);
	}
	
	public void setEnergyConsumptionFinalResult(Integer result) {
		energyconsumtionfinalresult=result;
		System.out.println("energyconsumtionfinalresult :"+energyconsumtionfinalresult);
	}
	
	public void setminiaotherizationresult(Double mniaoth) {
		miniaothorizationresult=mniaoth;
		System.out.println("miniaothorization :"+miniaothorizationresult);
	}
	
	public void setWasteGeneration(Integer wastegeneration) {
		this.wastegeneration=wastegeneration;
		System.out.println("wastegeneration :"+wastegeneration);
		analysingthemultipleanalytesinasinglerun=setAnalyzingMultipleAnalyticsInaSingleRun(this.wastegeneration,energyconsumtionfinalresult);
		System.out.println("analysingthemultipleanalytesinasinglerun :"+analysingthemultipleanalytesinasinglerun);
	}
	
	public Integer setAnalyzingMultipleAnalyticsInaSingleRun(Integer Wastegen,Integer Energuconsm) {
		return (Wastegen+Energuconsm)/2;
	}
}
