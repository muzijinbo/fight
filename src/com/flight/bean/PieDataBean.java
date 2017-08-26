package com.flight.bean;

public class PieDataBean {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isSliced() {
		return sliced;
	}
	public void setSliced(boolean sliced) {
		this.sliced = sliced;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	private String name;
	
	private double y;
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	private boolean sliced;
	private boolean selected;
	
}
