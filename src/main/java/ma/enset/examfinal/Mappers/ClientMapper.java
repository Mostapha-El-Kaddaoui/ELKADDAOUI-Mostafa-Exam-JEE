package ma.enset.examfinal.Mappers;

import ma.enset.examfinal.Dto.ClientDTO;
import ma.enset.examfinal.Entities.Client;
import ma.enset.examfinal.Entities.Credit;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClientMapper {
    public ClientDTO toDto(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        dto.setCreditsIds(client.getCredits().stream()
                .map(Credit::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return client;
    }
}
