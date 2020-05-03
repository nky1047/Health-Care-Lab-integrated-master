package org.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import org.com.dao.DiagnosticCentreRepositories;
import org.com.dao.TestRepositories;
import org.com.model.DiagnosticCentre;
import org.com.model.Test;

@SpringBootApplication
@EnableSwagger2
public class HealthCareSystemApplication {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("")).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(HealthCareSystemApplication.class, args);

		//ConfigurableApplicationContext ctx = SpringApplication.run(HealthCareSystemApplication.class, args);
		/*
		 * DiagnosticCentreRepositories dcr =
		 * ctx.getBean(DiagnosticCentreRepositories.class); TestRepositories tr =
		 * ctx.getBean(TestRepositories.class);
		 * 
		 * DiagnosticCentre diagnosticCentre = new DiagnosticCentre(); //
		 * diagnosticCentre.setDiagnosticCentreId(1231);
		 * diagnosticCentre.setCentreName("GZB");
		 * diagnosticCentre.setCentreAddress("Rajnagar");
		 * 
		 * List<Test> testList = new ArrayList<>();
		 * 
		 * Test test = new Test(); // test.setTestId(2231); test.setTestName("Liver");
		 * test.setTestDetails("Liver Testing"); test.setTestCost(3499.9f);
		 * 
		 * Test test2 = new Test(); // test2.setTestId(2232);
		 * test2.setTestName("Kidney"); test2.setTestDetails("Kidney Testing");
		 * test2.setTestCost(4999.0f);
		 * 
		 * tr.save(test); tr.save(test2);
		 * 
		 * testList.add(test); testList.add(test2); // System.out.println(testList);
		 * 
		 * diagnosticCentre.setListOfTests(testList); //
		 * System.out.println(diagnosticCentre.getListOfTests());
		 * 
		 * dcr.save(diagnosticCentre);
		 */
	}
}
