package com.fortune.service;

import com.fortune.model.Client;
import com.fortune.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public List<Client> findClientByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public void saveClient(Client client) {
            clientRepository.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
