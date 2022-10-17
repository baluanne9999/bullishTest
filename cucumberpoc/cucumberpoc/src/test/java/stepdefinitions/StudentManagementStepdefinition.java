package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import utilities.CommonUtilities;

import java.util.Map;

public class StudentManagementStepdefinition extends CommonUtilities {

    Map<String, Object> payload;

    @Given("I have base uri for student management api")
    public void i_have_base_uri_for_student_management_api() {
        setBASEURL(properties.getProperty("baseurl"));
    }

    @And("I set base path for student management {string} call")
    public void i_set_base_path_for_student_management_post_call(String apiMethod) {
        if (apiMethod.equalsIgnoreCase("post")){
            setBASEPATH("studentmgmt/addStudent");
        }
        else if (apiMethod.equalsIgnoreCase("put")) {
            setBASEPATH("studentmgmt/updateStudent");
        }
        else if (apiMethod.equalsIgnoreCase("get")) {
            setBASEPATH("studentmgmt/fetchStudents");
        }
        else if (apiMethod.equalsIgnoreCase("delete")) {
            setBASEPATH("studentmgmt/deleteStudent");
        }
        else {
            logger.info("Invalid data given for base path");
            Assert.fail("Invalid data given");
        }
    }

    @And("I set the payload for Student Management post api with id as {int} firstname {string} lastname {string} class {string} and nationality as {string}")
    public void i_set_the_payload_for_Student_Management_api(int id, String firstName, String lastName, String studentClass, String nationality ) {
        payload = addStudentPayload(id, firstName, lastName, studentClass, nationality);
    }

    @When("I make a POST request")
    public void i_make_a_post_request() {
        addStudentPostCall();
    }

    @Then("I should get the status code {int}")
    public void i_should_get_the_status_code(Integer statusCode) {
        validateStatusCode(statusCode);
    }

    @And("I verify the post response body with id as {int}")
    public void i_verify_the_post_response_body(int studentId) {
        verifyPostResponse(studentId);
    }

    @And("I set the payload for Student Management put api with id as {int} and class {string}")
    public void i_set_the_payload_for_Student_Management_put_api(int id, String studentClass) {
        payload = updateStudentPayload(id, studentClass);
    }

    @When("I make a PUT request")
    public void i_make_a_put_request() {
        updateStudentPutCall();
    }

    @And("I verify the put response body is returning id as {int} firstname {string} lastname {string} class {string} and nationality as {string}")
    public void i_verify_the_put_response_body(int studentId, String firstName, String lastName, String studentClass, String nationality) {
        verifyPutResponseBody(firstName, studentId, lastName, nationality, studentClass);
    }

    @When("^I make a GET request with \"(.*)\" and \"(.*)\"$")
    public void i_make_a_GET_request(int id, String strStudentClass) {
        fetchStudentsGetCall(id, strStudentClass);
    }

    @And("^I verify the GET response body with \"(.*)\" and \"(.*)\"$")
    public void i_verify_the_GET_response_body(int id, String strStudentClass) {
        validateGetResponse(id, strStudentClass);
    }

    @And("I set the payload for Student Management delete with id as {int}")
    public void i_set_the_payload_for_Student_Management_delete_api(int studentId) {
        deleteStudentPayload(studentId);
    }

    @When("I make a delete request")
    public void i_make_a_delete_request() {
        deleteStudentsCall();
    }

}
