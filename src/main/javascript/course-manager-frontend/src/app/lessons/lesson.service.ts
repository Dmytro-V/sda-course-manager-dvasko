import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {LessonBlock} from "./lesson-block";

@Injectable({
  providedIn: 'root'
})
export class LessonService {

  constructor(private http: HttpClient) { }

  assignTeacher(teacherId: number, lessonBlockId: number) {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', 'Basic ' + btoa('admin:admin'));

    this.http.get("/api/lesson-blocks/" + lessonBlockId, {headers: headers}).subscribe((block: LessonBlock) => {
      let body = {subject:block.subject, teacherId: teacherId};
      this.http.put('/api/lesson-blocks/' + lessonBlockId, body, {headers: headers}).subscribe();
    });
  }
}
