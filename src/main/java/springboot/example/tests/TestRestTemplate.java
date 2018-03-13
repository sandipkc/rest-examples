package springboot.example.tests;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import springboot.example.rest.template.usage.model.User;

public class TestRestTemplate {

	
	public static void main(String[] args) {
		TestRestTemplate testRest = new TestRestTemplate();
		testRest.runAllTests();
	}
	
	
	static void runAllTests() {

		//GET a Response Entity 

		//Using RestTemplate.GetForObject()
		
		String url = "http://localhost:8080/user/{id}";
		Map<String, String> params = new HashMap<>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject(url, User.class, params);

		System.out.println("GET User :" + user.getFirstName() + " " + user.getLastName());



		//Using RestTemplate.GetForEntity()
		
		url = "http://localhost:8080/user/1";
		 
		restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = restTemplate.headForHeaders(url);
		 
		ResponseEntity response = restTemplate.getForEntity(url, String.class);
		System.out.println(response);

	//	ObjectMapper objectMapper = new ObjectMapper();
	//	user = objectMapper.
	//			readValue(response.getBody(), User.class);

		//Using RestTemplate.PostForObject() 
		
		url = "http://localhost:8080/user";

		user = new User();
		user.setId(1L);
		user.setFirstName("John");
		user.setLastName("Doe");

		restTemplate = new RestTemplate();
		User result = restTemplate.postForObject(url, user, User.class);
		System.out.println(result.getFirstName() + " " + result.getLastName());

		//Using Delete to Delete a resource		
		url = "http://localhost:8080/user/{id}";

		params = new HashMap<>();
		params.put("id", "1");

		restTemplate = new RestTemplate();
		restTemplate.delete(url, params);


		//Using RestTemplate.Exchange() to send a POST
		url = "http://localhost:8080/user";
		restTemplate = new RestTemplate();

		user = new User();
		user.setId(1L);
		user.setFirstName("John");
		user.setLastName("Smith");

		HttpEntity<User> request = new HttpEntity<>(user);
		response = restTemplate.exchange(url, HttpMethod.POST, request, User.class);

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());

		//Using RetTemplate.PUT() to update a Request

		 url = "http://localhost:8080/user/{id}";

		params = new HashMap<>();
		params.put("id", "1");

		user = new User();
		user.setId(1L);
		user.setFirstName("John");
		user.setLastName("Smith");

		restTemplate = new RestTemplate();
		restTemplate.put(url, user, params);
	}
}
