import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CourseService} from "../course.service";

@Component({
  selector: 'app-course-enrollment-form',
  templateUrl: './course-enrollment-form.component.html',
  styleUrls: ['./course-enrollment-form.component.css']
})
export class CourseEnrollmentFormComponent implements OnInit {

  courseId: number;
  participantId: number;


  constructor(private route:ActivatedRoute,
              private courseService: CourseService) { }

  ngOnInit(): void {
    this.courseId = this.route.snapshot.queryParams['courseId'];
  }

  onSubmit() {
    this.courseService.assignParticipant(this.courseId, this.participantId);
  }
}
