package com.testsuite.southwests.Products;

	import java.lang.reflect.Method;
	import java.util.ArrayList;
	import java.util.List;

	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.Test;


	import com.api.category.SouthWestProduct;
	import com.base.BaseSetup;
	import com.datamanager.ConfigManager;
	import com.datamanager.ExcelManager;
	import com.testsuite.dataprovider.SouthwestUnitTests_TestData_Provider;


	public class  SouthWestGetAvailability extends BaseSetup {
		
//		Declaration of respective API Parts instances
		ExcelManager excel_Manager;
		SouthWestProduct southwestproduct;
		public ConfigManager api;
		
		public String currentTestName;
		List<String> headers = new ArrayList<String>();
		
		/**
		 * Purpose - Initializes the API parts instances
		 */
		@BeforeMethod(alwaysRun = true)
		public void SetUp(Method method) {
			
			api = new ConfigManager("Api");
			southwestproduct = new SouthWestProduct();
			currentTestName = method.getName();
			
		}
		
		/**
		 * 
		 */
		@Test(dataProviderClass=SouthwestUnitTests_TestData_Provider.class, dataProvider="TC001_getProductAvailability")
		public void tc_API_001_getProductAvailabilityUsingGetMethod(String endPoint, String partnerId, String month, String year) {
			
			southwestproduct.requestGetAvailability(currentTestName, endPoint, partnerId, month, year);
			
			southwestproduct.verifyDateRange("availabilityList", "date", month, year);
			
		}
		
	
	}



