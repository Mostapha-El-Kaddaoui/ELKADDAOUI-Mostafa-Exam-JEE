export enum StatutCredit {
    EN_COURS = 'EN_COURS',
    ACCEPTE = 'ACCEPTE',
    REJETE = 'REJETE'
  }
  
  export enum TypeCredit {
    PERSONNEL = 'PERSONNEL',
    IMMOBILIER = 'IMMOBILIER',
    PROFESSIONNEL = 'PROFESSIONNEL'
  }
  
  export interface Credit {
    id: number;
    dateDemande: Date;
    statut: StatutCredit;
    dateAcception?: Date;
    montant: number;
    dureeRemboursement: number;
    tauxInteret: number;
    clientId: number;
    type: TypeCredit;
  }
  
  export interface CreditPersonnel extends Credit {
    motif: string;
  }
  
  export interface CreditImmobilier extends Credit {
    typeBienFinance: string;
  }
  
  export interface CreditProfessionnel extends Credit {
    motif: string;
    raisonSociale: string;
  }