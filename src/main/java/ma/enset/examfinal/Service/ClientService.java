package ma.enset.examfinal.Service;

import ma.enset.examfinal.Dto.ClientDTO;
import ma.enset.examfinal.Dto.CreditDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClientById(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
}
