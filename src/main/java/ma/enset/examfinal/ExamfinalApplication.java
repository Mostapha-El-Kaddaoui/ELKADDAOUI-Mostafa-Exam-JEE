package ma.enset.examfinal;

import ma.enset.examfinal.Entities.*;
import ma.enset.examfinal.Enums.StatutCredit;
import ma.enset.examfinal.Enums.TypeBien;
import ma.enset.examfinal.Enums.TypeRemboursement;
import ma.enset.examfinal.Repositories.ClientRepository;
import ma.enset.examfinal.Repositories.CreditRepository;
import ma.enset.examfinal.Repositories.RemboursementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class ExamfinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamfinalApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(
            ClientRepository clientRepository,
            CreditRepository creditRepository,
            RemboursementRepository remboursementRepository) {

        return args -> {
            Client client1 = new Client(null,"Mohamed Ali", "m.ali@example.com",null);
            Client client2 = new Client(null,"Sophie Martin", "s.martin@example.com",null);
            Client client3 = new Client(null,"Jean Dupont", "j.dupont@example.com",null);

            clientRepository.saveAll(List.of(client1, client2, client3));

            // Cr perso
            CreditPersonnel cp1 = new CreditPersonnel();
            cp1.setMotif("Achat voiture");
            cp1.setClient(client1);
            cp1.setDateDemande(LocalDate.now().minusMonths(2));
            cp1.setStatut(StatutCredit.ACCEPTE);
            cp1.setDateAcception(LocalDate.now().minusMonths(1));
            cp1.setMontant(50000.0);
            cp1.setDureeRemboursement(60);
            cp1.setTauxInteret(4.5);

            // C immo
            CreditImmobilier ci1 = new CreditImmobilier();
            ci1.setTypeBienFinance(TypeBien.APPARTEMENT);
            ci1.setClient(client2);
            ci1.setDateDemande(LocalDate.now().minusMonths(3));
            ci1.setStatut(StatutCredit.EN_COURS);
            ci1.setMontant(250000.0);
            ci1.setDureeRemboursement(240);
            ci1.setTauxInteret(3.2);

            // C profess
            CreditProfessionnel cpro1 = new CreditProfessionnel();
            cpro1.setMotif("Equipement bureau");
            cpro1.setRaisonSociale("SARL TechSolutions");
            cpro1.setClient(client3);
            cpro1.setDateDemande(LocalDate.now().minusMonths(1));
            cpro1.setStatut(StatutCredit.ACCEPTE);
            cpro1.setDateAcception(LocalDate.now().minusDays(15));
            cpro1.setMontant(120000.0);
            cpro1.setDureeRemboursement(84);
            cpro1.setTauxInteret(5.0);

            creditRepository.saveAll(List.of(cp1, ci1, cpro1));

            // 3. Création de remboursements
            Remboursement r1 = new Remboursement();
            r1.setDate(LocalDate.now().minusDays(10));
            r1.setMontant(1200.0);
            r1.setType(TypeRemboursement.MENSUALITE);
            r1.setCredit(cp1);

            Remboursement r2 = new Remboursement();
            r2.setDate(LocalDate.now().minusDays(5));
            r2.setMontant(5000.0);
            r2.setType(TypeRemboursement.REMBOURSEMENT_ANTICIPE);
            r2.setCredit(cp1);

            Remboursement r3 = new Remboursement();
            r3.setDate(LocalDate.now().minusDays(20));
            r3.setMontant(850.0);
            r3.setType(TypeRemboursement.MENSUALITE);
            r3.setCredit(cpro1);

            remboursementRepository.saveAll(List.of(r1, r2, r3));
        };

}
}
