package com.example.restwsdemo.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ShirtsResponse {
	
	private List<Shirt> shirt;

	public List<Shirt> getShirt() {
		return shirt;
	}

	public void setShirt(List<Shirt> shirt) {
		this.shirt = shirt;
	}

}
