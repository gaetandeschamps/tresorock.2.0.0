package hei.tresorock.DAO;

import hei.tresorock.entities.Client;

import java.util.List;

public interface ClientDao {

    public List<Client> listClient();

    public Client getClient(Integer idClient);

    public int getClientId(Client client);

    public Client addClient(Client client);

}
