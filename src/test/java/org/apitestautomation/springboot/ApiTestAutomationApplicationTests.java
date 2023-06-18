package org.apitestautomation.springboot;

import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTestAutomationApplicationTests {
	private static final String ORDER_STATUS_NEW = "New";
	private static final String ORDER_STATUS_IN_PROGRESS = "InProgress";
	private static final String URL_PATH = "http://localhost:8080/api/v1/order";

	@Test
	void processOrder_OrderIdNotProvided_FailedInvalidRequest() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("order_description", "sample description");
		requestParams.put("order_status", ORDER_STATUS_NEW);
		requestParams.put("last_updated_timestamp", 1642321210439L);
		requestParams.put("special_order", false);

		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(URL_PATH)
				.then()
				.contentType(ContentType.JSON)
				.statusCode(400);
	}

	@Test
	void processOrder_OrderStatusNotProvided_FailedInvalidRequest() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("order_id", "xxxxxx");
		requestParams.put("order_description", "sample description");
		requestParams.put("last_updated_timestamp", 1642321210439L);
		requestParams.put("special_order", false);

		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(URL_PATH)
				.then()
				.contentType(ContentType.JSON)
				.statusCode(400);
	}

	@Test
	void processOrder_OrderStatusNotNew_FailedInvalidRequest() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("order_id", "xxxxxx");
		requestParams.put("order_description", "sample description");
		requestParams.put("order_status", ORDER_STATUS_IN_PROGRESS);
		requestParams.put("last_updated_timestamp", 1642321210439L);
		requestParams.put("special_order", false);

		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(URL_PATH)
				.then()
				.contentType(ContentType.JSON)
				.statusCode(400);
	}

	@Test
	void processOrder_CorrectPayload_Success() {
		JSONObject requestParams = getOrderAsBodyParam(ORDER_STATUS_NEW);
		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(URL_PATH)
				.then()
				.contentType(ContentType.JSON)
				.body("order_id", equalTo("xxxxxx"))
				.body("order_status", equalTo(ORDER_STATUS_IN_PROGRESS))
				.body("last_updated_timestamp", greaterThan(1642321210439L))
				.statusCode(200);
	}

	private JSONObject getOrderAsBodyParam(String orderStatus) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("order_id", "xxxxxx");
		requestParams.put("order_description", "sample description");
		requestParams.put("order_status", orderStatus);
		requestParams.put("last_updated_timestamp", 1642321210439L);
		requestParams.put("special_order", false);
		return requestParams;
	}
}
