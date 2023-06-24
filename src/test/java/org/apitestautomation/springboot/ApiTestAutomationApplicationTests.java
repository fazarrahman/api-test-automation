package org.apitestautomation.springboot;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTestAutomationApplicationTests extends TestBase {
	private static final String ORDER_STATUS_NEW = "New";
	private static final String ORDER_STATUS_IN_PROGRESS = "InProgress";

	@AfterClass(alwaysRun = true)
	public void cleanup() {
		RestAssured.reset();
	}

	@Test(priority = 1)
	void processOrder_CorrectPayload_Success() {
		JSONObject requestParams = getOrderAsBodyParam(ORDER_STATUS_NEW);
		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(getUrlPath())
				.then()
				.contentType(ContentType.JSON)
				.body("order_id", equalTo("xxxxxx"))
				.body("order_status", equalTo(ORDER_STATUS_IN_PROGRESS))
				.body("last_updated_timestamp", greaterThan(1642321210439L))
				.statusCode(200);
	}

	@Test(priority = 2)
	void processOrder_OrderIdNotProvided_InvalidRequest() {
		JSONObject requestParams = getOrderAsBodyParam(ORDER_STATUS_NEW);
		requestParams.remove("order_id");

		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(getUrlPath())
				.then()
				.contentType(ContentType.JSON)
				.statusCode(400);
	}

	@Test(priority = 3)
	void processOrder_OrderStatusNotProvided_InvalidRequest() {
		JSONObject requestParams = getOrderAsBodyParam(ORDER_STATUS_NEW);
		requestParams.remove("order_status");

		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(getUrlPath())
				.then()
				.contentType(ContentType.JSON)
				.statusCode(400);
	}

	@Test(priority = 4)
	void processOrder_OrderStatusNotNew_InvalidRequest() {
		JSONObject requestParams = getOrderAsBodyParam(ORDER_STATUS_IN_PROGRESS);

		given().accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.when()
				.post(getUrlPath())
				.then()
				.contentType(ContentType.JSON)
				.statusCode(400);
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
