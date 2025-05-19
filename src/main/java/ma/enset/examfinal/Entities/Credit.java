package ma.enset.examfinal.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.examfinal.Enums.StatutCredit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "credit_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDemande;
    private StatutCredit statut;
    private LocalDate dateAcception;
    private Double montant;
    private Integer dureeRemboursement; // en mois
    private Double tauxInteret;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    private List<Remboursement> remboursements = new ArrayList<>();


}

