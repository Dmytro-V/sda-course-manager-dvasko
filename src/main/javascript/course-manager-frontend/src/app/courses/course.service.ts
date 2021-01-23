import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Course} from "./course";
import {CourseDetails} from "./course-details";

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Course[]> {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<Course[]>('/api/courses/', {headers: headers});
  }

  findById(id: number): Observable<CourseDetails>{
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    return this.http.get<CourseDetails>('/api/courses/' + id + '/details', {headers: headers});
  }

  assignParticipant(courseId: number, participantId: number) {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    let body = {courseId:courseId, participantId: participantId};
    this.http.post('/api/enrollments/', body, {headers: headers}).subscribe();
  }
}
