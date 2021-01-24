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

    return this.http.get<Course[]>('/api/courses/');
  }

  findById(id: number): Observable<CourseDetails>{

    return this.http.get<CourseDetails>('/api/courses/' + id + '/details');
  }

  assignParticipant(courseId: number, participantId: number) {
    let body = {courseId:courseId, participantId: participantId};
    this.http.post('/api/enrollments/', body).subscribe();
  }
}
