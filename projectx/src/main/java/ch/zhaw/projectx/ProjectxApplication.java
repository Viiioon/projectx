package ch.zhaw.projectx;

import ch.zhaw.projectx.repository.BeliefRepository;
import ch.zhaw.projectx.service.DataLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectxApplication.class, args);
		System.out.println("hello world");
	}

}
