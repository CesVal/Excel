package pe.com.access.control.AccesControl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.com.access.control.AccesControl.entity.User;
import pe.com.access.control.AccesControl.repository.UserRepository;

@SpringBootApplication
public class AccessControlApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccessControlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = userRepository.userById("1");
		System.out.println("finish");
	}
}
