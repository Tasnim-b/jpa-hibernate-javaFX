package org.service;

import org.dao.GenericDAO;
import org.model.Client;

public class ClientService {
    private GenericDAO<Client> clientDAO;

    public ClientService() {
        this.clientDAO = new GenericDAO<>(Client.class);
    }

    public void updateClient(Client client) {
        clientDAO.update(client);
    }

    public void addClient(Client client) {
        clientDAO.add(client);
    }

    public Client getClientById(Long id) {
        return clientDAO.getById(id);
    }

    public void deleteClient(Long id) {
        clientDAO.delete(id);
    }
}
