package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="DoorStep_Services")
public class Services {
 
	private int serviceId;
	private String serviceName;
	private double rate;
	private ServiceCategory serviceCategory;
	
	public Services() {
		System.out.println("in services def constr");
	}
	@Id
	@GeneratedValue
	@Column(name="Service_Id_PK",length=10)
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	@ManyToOne
	@JoinColumn(name="SCategories_Id_PK")
	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	@Column(name="Service_Name",length=25)
	public String getServiceName() {
		return serviceName;
	}

	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	@Column(name="Service_Rate",columnDefinition="decimal(6,2)")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "Services [serviceId=" + serviceId + ", categoryId="
				+ serviceCategory.toString() + ", serviceName=" + serviceName + ", Rate="
				+ rate +"]";
	}

	
	
	
	
	
	
}
