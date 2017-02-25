package com.itunes.search.api;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Class that executes tests for iTunes search API.
 * 
 * @author ushab<usha.brahma.pr@gmail.com>
 */
public class iTuneSearchAPI {	

	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "com.itunes.search.api.iTuneSearchAPI");            
	}
	
	/* 
	 * Test Case1: iTunesearchApi1
	 * The API will return 200 response when required parameter is passed 
	 */	
	
	@Test
	public void iTunesearchApi1 () {
		try {
			Client client = Client.create();
			WebResource webRsc = client
					.resource("https://itunes.apple.com/search?term=tool");
			ClientResponse response = webRsc.accept("application/json").get(ClientResponse.class);
			//assert response
			assertEquals(200,response.getStatus());
			String output = response.getEntity(String.class);
			System.out.println(output);
			JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );					
			if (object != null) {
				@SuppressWarnings("unchecked")
				Iterator<String> it = object.keys();
				while (it.hasNext()) {
					String key = it.next();
					
					if("resultCount".equalsIgnoreCase(key)){
                        System.out.println(object.get(key));
                        break;
					}
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/* 
	 * Test Case2: iTunesearchApi2
	 * The API should return 200 when when all optional param's like country, media,limit =100 are mentioned 
	 */
	
	@Test
	public void iTunesearchApi2 () {
		try {
			Client client = Client.create();
			int count = 0;
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool&country=US&media=music&limit=100");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(200,response.getStatus());
			String output = response.getEntity(String.class);			
			JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );							
			if (object != null) {
				@SuppressWarnings("unchecked")
				Iterator<String> it = object.keys();
				while (it.hasNext()) {
					String key = it.next();					
					if("resultCount".equalsIgnoreCase(key)){
                        count = (int) object.get(key);
                        break;
					}
				}
			}	
			//assert result count
			assertEquals(100,count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * Test Case3: iTunesearchApi3
	 * The api should return 200 when when one optional param's  limit =100 is mentioned
	 */
	
	@Test
	public void iTunesearchApi3 () {
		try {
			Client client = Client.create();
			int count = 0;
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool&country=US&media=music&limit=100");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(200,response.getStatus());
			String output = response.getEntity(String.class);
			JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );					
			if (object != null) {
				@SuppressWarnings("unchecked")
				Iterator<String> it = object.keys();
				while (it.hasNext()) {
					String key = it.next();					
					if("resultCount".equalsIgnoreCase(key)){
                        count = (int) object.get(key);
                        break;
					}
				}
			}	
			//assert result count
			assertEquals(100,count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * Test Case4: iTunesearchApi4
	 * The api should return 400 when country code is invalid 
	 */
	
	@Test
	public void iTunesearchApi4 () {
		try {
			Client client = Client.create();
			WebResource webResource = client
					.resource("https://itunes.apple.com/search?term=tool&country=Z!");
			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);
			//assert response
			assertEquals(400,response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
		/* 
		 * Test Case5: iTunesearchApi5
		 * The api should return 400 when media is invalid 
		 */
	
		@Test
		public void iTunesearchApi5 () {
			try {
				Client client = Client.create();
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&media=123");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				//assert response
				assertEquals(400,response.getStatus());
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		}
		
		/* 
		 * Test Case6: iTunesearchApi6
		 * The api should return 200 when limit is invalid default it to 50
		 */
		@Test
		public void iTunesearchApi6 () {
			try {
				Client client = Client.create();
				int count =0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&limit=abc");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				if (object != null) {
					@SuppressWarnings("unchecked")
					Iterator<String> it = object.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) object.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(50,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	
		/* 
		 * Test Case7: iTunesearchApi7
		 * The api should return 200 when limit is greater(greater than 200) default it to 200
		 */
		
		@Test
		public void iTunesearchApi7 () {
			try {
				Client client = Client.create();
				int count =0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&limit=400");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				if (object != null) {
					@SuppressWarnings("unchecked")
					Iterator<String> it = object.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) object.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(200,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		/* 
		 * Test Case8: iTunesearchApi8
		 * The api should return 200 when limit is negative(less than 0) default it to 0
		 */
		
		@Test
		public void iTunesearchApi8 () {
			try {
				Client client = Client.create();
				int count = 0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool&limit=-18");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				if (object != null) {
					@SuppressWarnings("unchecked")
					Iterator<String> it = object.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) object.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(0,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		
	
		/* 
		 * Test Case9: iTunesearchApi9
		 * The api should return 200 when term is invalid, resultcount is 0
		 */
		
		@Test
		public void iTunesearchApi9 () {
			try {
				Client client = Client.create();
				int count =0;
				WebResource webResource = client
						.resource("https://itunes.apple.com/search?term=tool*maynard");
				ClientResponse response = webResource.accept("application/json")
						.get(ClientResponse.class);
				assertEquals(200,response.getStatus());
				String output = response.getEntity(String.class);
				JSONObject object = (JSONObject) JSONSerializer.toJSON( output.trim() );					
				if (object != null) {
					@SuppressWarnings("unchecked")
					Iterator<String> it = object.keys();
					while (it.hasNext()) {
						String key = it.next();					
						if("resultCount".equalsIgnoreCase(key)){
	                        count = (int) object.get(key);
	                        break;
						}
					}
				}	
				//assert result count
				assertEquals(0,count);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	
}