package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
}
