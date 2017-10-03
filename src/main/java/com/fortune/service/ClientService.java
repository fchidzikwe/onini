package com.fortune.service;


import com.fortune.model.Client;

import java.util.List;

public interface ClientService {

    Client findClientByEmail(String email);

     List<Client> findClientByLastName(String lastName);

     List<Client> findByLastNameLike(String lastName);

    Client findClientById(Long id);

     void saveClient(Client client);

    void deleteClient(Client client);

     List<Client> findAll();

     List<Client> findByNameOrLastName(String searchTerm);

}
