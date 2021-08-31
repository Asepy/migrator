package py.com.asepy.migrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import py.com.asepy.migrator.core.Migrator;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MigratorApplication {

	@Autowired
	private Migrator migrator;

	public static void main(String[] args) {
		SpringApplication.run(MigratorApplication.class, args);
	}

	@PostConstruct
	public void initMigration() throws Exception{
		migrator.startMigration();
	}

}
