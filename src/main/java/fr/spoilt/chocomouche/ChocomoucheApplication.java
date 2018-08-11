package fr.spoilt.chocomouche;

import fr.spoilt.chocomouche.game.Board;
import lombok.extern.java.Log;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class ChocomoucheApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ChocomoucheApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);


	}
}
