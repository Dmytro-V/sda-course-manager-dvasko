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

    return this.http.get<User[]>('/api/users/');
  }


  findById(id: number): Observable<UserDetails>{

    return this.http.get<UserDetails>('/api/users/' + id);
  }

  findAllTeachers(): Observable<User[]> {

    return this.http.get<User[]>('/api/users/'+'?type=TEACHER');
  }

  findAllParticipants(): Observable<User[]> {

    return this.http.get<User[]>('/api/users/'+'?type=PARTICIPANT');
  }
}
