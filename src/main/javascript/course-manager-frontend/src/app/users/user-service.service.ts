import { Injectable } from '@angular/core';
import {User} from "./user";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserDetails} from "./user-details";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<User[]> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<User[]>('/api/users/', {headers: headers});
  }


  findById(id: number): Observable<UserDetails>{
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<UserDetails>('/api/users/' + id, {headers: headers});
  }

  findAllTeachers(): Observable<User[]> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<User[]>('/api/users/'+'?type=TEACHER', {headers: headers});
  }

  findAllParticipants(): Observable<User[]> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<User[]>('/api/users/'+'?type=PARTICIPANT', {headers: headers});
  }
}
