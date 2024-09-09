package humanclound.commentservice.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class CommentServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CommentServiceApplication.class, args);
	}

}
