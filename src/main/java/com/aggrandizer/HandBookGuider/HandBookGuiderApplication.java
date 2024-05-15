package com.aggrandizer.HandBookGuider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@CommandScan
@SpringBootApplication
public class HandBookGuiderApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandBookGuiderApplication.class, args);
	}

}
