package com.training.computerDatabase;


	import static io.restassured.RestAssured.given;
	import static org.hamcrest.Matchers.containsString;

	import java.io.IOException;

	import org.testng.annotations.Test;

	import io.restassured.response.Response;

	public class ApiTest {

		Computers comp = new Computers();

		@Test
		public void gettingDataFromInput() throws IOException {
			Response rep = given().queryParam("f", comp.Attributes("ComputerName")).when().get(comp.Attributes("URL"));
			System.out.print(rep.asString());
			String param = rep.htmlPath().getString("html.body.section.table.tbody.tr.td[0].a.@href").split("/")[2];

			Response response = given().pathParam("CompNumber", param).when().get(comp.Attributes("URL") + "/{CompNumber}");

			response.then().body("html.body.section.form.fieldset.div.div[0].input.@value",
					containsString(comp.Attributes("ComputerName")),
					"html.body.section.form.fieldset.div[1].div[0].input.@value",
					containsString(comp.Attributes("IntroducedDate")),
					"html.body.section.form.fieldset.div[2].div[0].input.@value",
					containsString(comp.Attributes("DiscontinuedDate")),
					"html.body.section.form.fieldset.div[3].div[0].select.option[3]",
					containsString(comp.Attributes("Company")));

		}

	}



