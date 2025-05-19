package ma.enset.examfinal.Web;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.examfinal.Dto.ClientDTO;
import ma.enset.examfinal.Dto.CreditDTO;
import ma.enset.examfinal.Dto.RemboursementDTO;
import ma.enset.examfinal.Enums.StatutCredit;
import ma.enset.examfinal.Service.ClientService;
import ma.enset.examfinal.Service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/credits")

public class CreditRestController {

    @Autowired
    private CreditService creditService;

    @PostMapping
    public ResponseEntity<CreditDTO> createCredit(@Valid @RequestBody CreditDTO creditDTO) {
        CreditDTO createdCredit = creditService.createCredit(creditDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdCredit);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/remboursements")
    public ResponseEntity<List<RemboursementDTO>> getCreditRemboursements(@PathVariable Long id) {
        List<RemboursementDTO> remboursements = creditService.getCreditRemboursements(id);
        return ResponseEntity.ok(remboursements);
    }
}
