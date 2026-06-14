package com.wellsfargo.counselor.repository;

import com.wellsfargo.counselor.entity.Client;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    // Custom query looking up clients via their mapped Advisor's last name
    List<Client> findByAdvisorLastName(String lastName);
}