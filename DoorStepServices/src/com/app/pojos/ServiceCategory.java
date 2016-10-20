package com.app.pojos;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="DoorStep_Services_Categories")
public class ServiceCategory {

	private int serviceCategoryId;
	private String serviceCategoryName;
	public Set<Services> services = new HashSet<>();
	public Set<VendorTimePojo> vendorTimes = new HashSet<VendorTimePojo>(); 
	public Set<Vendor> vendors = new HashSet<>();
	
	public ServiceCategory() {
		System.out.println("in category def constr");
	}
	
	@Id
	@GeneratedValue
	@Column(name="SCategories_Id_PK",length=10)
	public int getServiceCategoryId() {
		return serviceCategoryId;
	}

	public void setServiceCategoryId(int serviceCategoryId) {
		this.serviceCategoryId = serviceCategoryId;
	}
	
	@Column(name="SCategory_Name_UK",unique=true,length=25)
	public String getServiceCategoryName() {
		return serviceCategoryName;
	}

	public void setServiceCategoryName(String serviceCategoryName) {
		this.serviceCategoryName = serviceCategoryName;
	}
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceCategory" ,fetch = FetchType.EAGER)
	public Set<Services> getServices() {
		return services;
	}
	public void setServices(Set<Services> services) {
		this.services = services;
	}

	@OneToMany(cascade=CascadeType.ALL,mappedBy="serviceCategory")
	public Set<VendorTimePojo> getVendorTimes() {
		return vendorTimes;
	}

	public void setVendorTimes(Set<VendorTimePojo> vendorTimes) {
		this.vendorTimes = vendorTimes;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceCategory" ,fetch = FetchType.EAGER)
	public Set<Vendor> getVendors() {
		return vendors;
	}
	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}
	
	public boolean addServices(Services serviceObj )
	{
		if(services.add(serviceObj))
		{
			serviceObj.setServiceCategory(this);
			return true;
		}
		return false;
	}
	
	public boolean addVendor(Vendor vendorObj )
	{
		if(vendors.add(vendorObj))
		{
			vendorObj.setServiceCategory(this);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "ServiceCategory [serviceCategoryId=" + serviceCategoryId
				+ ", serviceCategoryName=" + serviceCategoryName + "]";
	}
	
	
	
	
}
