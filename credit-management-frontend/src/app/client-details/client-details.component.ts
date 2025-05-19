import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClientService } from './../services/client.service';
import { CreditService } from './../services/credit.service';
import { Client } from './../models/client.model';
import { Credit } from './../models/credit.model';
import { catchError, finalize, Observable, throwError } from 'rxjs';

@Component({
  selector: 'app-client-details',
  templateUrl: './client-details.component.html',
  styleUrls: ['./client-details.component.css']
})
export class ClientDetailsComponent implements OnInit {
  clients$!: Observable<Array<Client>>;
  credits: Credit[] = [];
  newCreditMode = false;
  errorMessage: any;

  constructor(
    private route: ActivatedRoute,
    private clientService: ClientService,
    private creditService: CreditService
  ) { }

  ngOnInit(): void {
    this.clients$ = this.clientService.getClients().pipe(
      catchError(err=>{
        this.errorMessage = err.message;
        return throwError(err);
      }),
    );
  }

  

}