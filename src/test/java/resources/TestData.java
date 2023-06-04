package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Addplacepojo;
import pojo.Locationpojo;

public class TestData {
	
	public Addplacepojo Testpayload(String name, String language, String Address) {
	
	Addplacepojo ap = new Addplacepojo();
	ap.setAccuracy(30);
	ap.setAddress(Address);
	ap.setLanguage(language);
	ap.setName(name);
	ap.setPhone_number("(+91) 983 893 3937");
	ap.setWebsite("www.rahulshettyacademy.com");
	
	List<String> mylist = new ArrayList<String>();
	mylist.add("shoe flower");
	mylist.add("Mall");
	ap.setTypes(mylist);
	
	Locationpojo l = new Locationpojo();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	ap.setLocation(l);
	
	return ap;
}
	
	public String deletepayload(String placeID) {
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}";
	}
}