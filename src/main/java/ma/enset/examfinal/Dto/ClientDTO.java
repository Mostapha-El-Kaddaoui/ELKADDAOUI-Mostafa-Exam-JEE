package ma.enset.examfinal.Dto;

import lombok.Data;

import java.util.List;
@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
    private List<Long> creditsIds;
}