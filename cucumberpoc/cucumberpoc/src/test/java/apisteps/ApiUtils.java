package apisteps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.*;

public class ApiUtils {

    public static Logger logger = LoggerFactory.getLogger(ApiUtils.class);
    public Response response;

    public static Map<String, Object> payload;


    // This function will set the base url
    public void setBASEURL(String baseuri) {
        logger.info("Set base uri " + baseuri);
        RestAssured.baseURI = baseuri;
    }

    // This function will set the base path
    public void setBASEPATH(String basePath) {
        logger.info("set basepath " + basePath);
        RestAssured.basePath = basePath;
    }

    // This function send the post call Add new student in the Database
    public void addStudentPostCall() {
        logger.info("add student post call");
        response = RestAssured.given().body(payload).contentType(ContentType.JSON).log().all()
                .post();
    }

    // This function will validates status code from the response
    public void validateStatusCode(int expStatusCode) {
        logger.info("Validate response status code");
        Assert.assertEquals(response.statusCode(), expStatusCode, "Getting invalid status code in response");
    }

    // This function will verify the post call response
    public void verifyPostResponse(int studentId) {
        logger.info("Validate post call response body.");
        Assert.assertTrue(response.asString().contains("New student enrolled with student id : " + studentId), "Student id is not matching in response body");
    }

    // This function will send the put call to Update Student Record in Database
    public void updateStudentPutCall() {
        logger.info("update student put call");
        response = RestAssured.given().body(payload).contentType(ContentType.JSON).log().all()
                .put();
    }

    // This function will verify the response from the put call
    public void verifyPutResponseBody(String firstName, int studentId, String lastName, String nationality, String studentClass) {
        logger.info("Verify update students response body");
        Assert.assertEquals(firstName, response.jsonPath().get("firstName").toString(), "First name is not updated in response body");
        Assert.assertEquals(studentId, Integer.parseInt(response.jsonPath().get("id").toString()), "Student is updated in response body");
        Assert.assertEquals(lastName, response.jsonPath().get("lastName").toString(), "LastName name is not updated in response body");
        Assert.assertEquals(nationality, response.jsonPath().get("nationality").toString(), "Nationality name is not updated in response body");
        Assert.assertEquals(studentClass, response.jsonPath().get("studentClass").toString(), "Student Class name is not updated in response body");
    }

    // This function will set the payload for post and put call to Add student in the Database
    public Map<String, Object> addStudentPayload(int studentId, String firstName, String lastName, String studentClass, String nationality) {
        logger.info("add students payload");
        payload = new LinkedHashMap<>();
        payload.put("firstName", firstName);
        payload.put("id", studentId);
        payload.put("lastName", lastName);
        payload.put("nationality", nationality);
        payload.put("studentClass", studentClass);
        return payload;
    }

    // This function will set the payload for put and put call to update student in the Database
    public Map<String, Object> updateStudentPayload(int studentId, String studentClass) {
        logger.info("add students payload");
        payload = new LinkedHashMap<>();
        payload.put("id", studentId);
        payload.put("studentClass", studentClass);
        return payload;
    }

    // This function performs the get call to Fetch Student Record from Database
    public void fetchStudentsGetCall(int id, String studentClass) {
        logger.info("fetch students data get method");
        HashMap<String, String> map = new HashMap<String, String>();
        if (id > 0) {
            map.put("id", Integer.toString(id));
        }
        if (!studentClass.isEmpty()) {
            map.put("studentClass", studentClass);
        }
        response = RestAssured.given().contentType(ContentType.JSON).
                queryParams(map).
                log().all()
                .get();
    }

    // This function verifies the get response
    public void validateGetResponse(int actualid, String studentClass) {

        logger.info("Verify Get call reponse data");

        if (!response.equals("[]")) {

            if (actualid <= 0 && studentClass.isEmpty()) {
                Assert.assertTrue(response.getBody().jsonPath().getList("id").size() > 0, "Getting students data in response");
            }

            if (actualid > 0 && studentClass.isEmpty()) {
                Assert.assertEquals("[" + actualid + "]", response.jsonPath().getString("id"), "Student id is not matching in response data");
            }

            if (actualid <= 0 && !studentClass.isEmpty()) {
                for (int i = 0; i < response.getBody().jsonPath().getList("$").size(); i++) {
                    String actualStudentClass = response.jsonPath().getString("studentClass[" + i + "]");
                    Assert.assertEquals(studentClass, actualStudentClass, "Student class is not matching in response data");
                }
            }

            if (actualid > 0 && !studentClass.isEmpty()) {
                Assert.assertEquals("[" + actualid + "]", response.jsonPath().getString("id"), "Student id is not matching in response data");
                Assert.assertEquals("[" + studentClass + "]", response.jsonPath().getString("studentClass"), "Student class is not matching in response data");
            }

        } else {
            Assert.assertFalse(response.equals("[]"), "We dont have any student records");
        }
    }

    // This function will set the payload for delete call to Delete Student Record from Database
    public Map<String, Object> deleteStudentPayload(int studentId) {
        logger.info("Delete student payload");
        payload = new LinkedHashMap<>();
        payload.put("id", studentId);
        return payload;
    }

    // This function performs the delete call to Delete Student Record from Database
    public void deleteStudentsCall() {
        logger.info("Delete student delete api call");
        response = RestAssured.given().contentType(ContentType.JSON).body(payload).
                log().all()
                .delete();
    }


}
