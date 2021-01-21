import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CourseService} from "../course.service";
import {User} from "../../users/user";
import {UserService} from "../../users/user-service.service";

@Component({
  selector: 'app-course-enrollment-form',
  templateUrl: './course-enrollment-form.component.html',
  styleUrls: ['./course-enrollment-form.component.css']
})
export class CourseEnrollmentFormComponent implements OnInit {

  courseId: number;
  participantId: number;
  participants: User[];


  constructor(private route:ActivatedRoute,
              private courseService: CourseService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.courseId = this.route.snapshot.queryParams['courseId'];
    this.userService.findAllParticipants().subscribe(data => {
      this.participants = data;
    });
  }

  onChange(newValue: number) {
    this.participantId = newValue;
  }

  onSubmit() {
    this.courseService.assignParticipant(this.courseId, this.participantId);
  }
}
