import { Component, OnInit } from '@angular/core';
import {CourseService} from "../course.service";
import {Course} from "../course";

@Component({
  selector: 'app-courses-list',
  templateUrl: './courses-list.component.html',
  styleUrls: ['./courses-list.component.css']
})
export class CoursesListComponent implements OnInit {

  courses: Course[];

  constructor(private courseService: CourseService) {
  }

  ngOnInit() {
    this.courseService.findAll().subscribe(data => {
      this.courses = data;
    });
  }

}
