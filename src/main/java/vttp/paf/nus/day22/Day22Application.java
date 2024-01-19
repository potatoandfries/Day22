package vttp.paf.nus.day22;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.paf.nus.day22.Repo.TaskRepo;


@SpringBootApplication
public class Day22Application {
	
	@Autowired
	TaskRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(Day22Application.class, args);
	}

}
