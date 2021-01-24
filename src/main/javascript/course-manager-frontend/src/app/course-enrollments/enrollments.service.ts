import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {Enrollment} from "./enrollment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EnrollmentsService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Enrollment[]> {

    return this.http.get<Enrollment[]>('/api/enrollments/');

  }
}
