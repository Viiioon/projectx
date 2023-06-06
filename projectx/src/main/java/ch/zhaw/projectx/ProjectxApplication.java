package ch.zhaw.projectx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
public class ProjectxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectxApplication.class, args);
		System.out.println("hello world");
	}

}
