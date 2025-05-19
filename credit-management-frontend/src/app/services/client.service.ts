import { Injectable } from '@angular/core';
import { Client } from '../models/client.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(private http:HttpClient, private api: ApiService) { }
  apiUrl:string="http://localhost:8085"
  

  getClients(): Observable<Array<Client>> {
    return this.http.get<Array<Client>>(this.apiUrl+"/clients");
  }
  

  getClient(id: number): Observable<Client> {
    return this.api.get(`clients/${id}`);
  }

  createClient(client: Client): Observable<Client> {
    return this.api.post('clients', client);
  }

  updateClient(id: number, client: Client): Observable<Client> {
    return this.api.put(`clients/${id}`, client);
  }

  deleteClient(id: number): Observable<any> {
    return this.api.delete(`clients/${id}`);
  }
}