package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.Contract;
import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.repos.ContractRepository;
import com.UHK.garbagecollectors.repos.GCanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private ContractRepository repo;

    @Autowired
    public ContractService(ContractRepository repo){
        this.repo = repo;
    }

    public void add(Contract newObj) {
        repo.save(newObj);
    }
}
