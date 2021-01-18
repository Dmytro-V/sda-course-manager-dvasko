import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CourseService} from "../course.service";
import {CourseDetails} from "../course-details";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  public course: CourseDetails;

  constructor(private courseService: CourseService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = this.route.snapshot.params['id'];
    this.courseService.findById(id).subscribe(data => {
      this.course = data;
    });
  }

}
