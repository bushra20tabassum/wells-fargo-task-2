package com.wellsfargo.counselor;

import com.wellsfargo.counselor.entity.Advisor;
import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.repository.AdvisorRepository;
import com.wellsfargo.counselor.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Entrypoint {

    private static final Logger logger = LoggerFactory.getLogger(Entrypoint.class);

    public static void main(String[] args) {
        SpringApplication.run(Entrypoint.class, args);
    }

    @Bean
    public CommandLineRunner demo(AdvisorRepository advisorRepository, ClientRepository clientRepository) {
        return (args) -> {
            // 1. Create and Save an Advisor
            Advisor sampleAdvisor = new Advisor("John", "Doe", "123 Financial Way", "555-0199", "john.doe@wellsfargo.com");
            advisorRepository.save(sampleAdvisor);

            // 2. Create and Save a Client assigned to that Advisor
            Client sampleClient = new Client(sampleAdvisor, "Jane", "Smith", "456 Market St", "555-0122", "jane.smith@client.com");
            clientRepository.save(sampleClient);

            // 3. Query and Log information to console
            logger.info("--------------------------------------------");
            logger.info("Advisors found with findAll():");
            advisorRepository.findAll().forEach(advisor -> {
                logger.info("Advisor: {} {}", advisor.getFirstName(), advisor.getLastName());
            });

            logger.info("--------------------------------------------");
            logger.info("Clients found for Advisor Doe:");
            clientRepository.findByAdvisorLastName("Doe").forEach(client -> {
                logger.info("Client managed: {} {}", client.getFirstName(), client.getLastName());
            });
            logger.info("--------------------------------------------");
        };
    }
}
