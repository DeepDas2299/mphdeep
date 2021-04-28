package com.mph.model;

import java.io.Serializable;

public class Salary implements Serializable{
	private int basic;
	private double da;
	private double hra;
	private double pf;
	private double gross;
	private double net;
	
	public Salary() {}
	public Salary(int basic)
	{
		this.basic = basic;
		setDa(basic);
		setHra(basic);
		setPf(basic);
		this.da = getDa();
		this.hra = getHra();
		this.pf = getPf();
		setGross(this.basic, this.da, this.hra);
		setNet(this.gross, this.pf);
	}
	public int getBasic() {
		return basic;
	}
	public void setBasic(int basic) {
		this.basic = basic;
	}
	
	public double getDa() {
		return da;
	}
	public void setDa(int basic) {
		this.da = 0.3 * basic;
	}
	public double getHra() {
		return hra;
	}
	public void setHra(int basic) {
		this.hra = 0.4 * basic;
	}
	public double getPf() {
		return pf;
	}
	public void setPf(int basic) {
		this.pf = 0.06 * basic;
	}
	public double getGross() {
		return gross;
	}
	public void setGross(int basic, double da, double hra) {
		this.gross = basic + da + hra;
	}
	public double getNet() {
		return net;
	}
	public void setNet(double gross, double pf) {
		this.net = gross - pf;
	}
	@Override
	public String toString() {
		return "Salary basic=" + basic + ", da=" + da + ", hra=" + hra + ", pf=" + pf + ", gross=" + gross + ", net="
				+ net;
	}
	
}
