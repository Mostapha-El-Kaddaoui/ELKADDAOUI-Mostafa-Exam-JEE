package ma.enset.examfinal.Service;

import jakarta.transaction.Transactional;
import ma.enset.examfinal.Dto.ClientDTO;
import ma.enset.examfinal.Dto.CreditDTO;
import ma.enset.examfinal.Entities.Client;
import ma.enset.examfinal.Mappers.ClientMapper;
import ma.enset.examfinal.Mappers.CreditMapper;
import ma.enset.examfinal.Repositories.ClientRepository;
import ma.enset.examfinal.Repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private CreditMapper creditMapper;

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        existingClient.setNom(clientDTO.getNom());
        existingClient.setEmail(clientDTO.getEmail());

        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getClientCredits(Long clientId) {
        return creditRepository.findByClientId(clientId).stream()
                .map(creditMapper::toDto)
                .collect(Collectors.toList());
    }
}