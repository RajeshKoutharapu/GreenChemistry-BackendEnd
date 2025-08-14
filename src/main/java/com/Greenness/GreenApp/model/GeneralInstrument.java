package com.Greenness.GreenApp.model;

public class GeneralInstrument {
    private String instrument;
    private Double time;
    private Double energy_kwh;
    // Getters and setters
    
    public String getInstrument() {
        return instrument;
    }

    public Double getEnergy_kwh() {
		return energy_kwh;
	}

	public void setEnergy_kwh(Double energy_kwh) {
		this.energy_kwh = energy_kwh;
	}

	public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
