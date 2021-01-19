import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Enrollment} from "./enrollment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Course} from "../courses/course";

@Injectable({
  providedIn: 'root'
})
export class EnrollmentsService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Enrollment[]> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<Enrollment[]>('/api/enrollments/', {headers: headers});

  }
}
