import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LessonService} from "../../lessons/lesson.service";
import {User} from "../../users/user";
import {UserService} from "../../users/user-service.service";

@Component({
  selector: 'app-assign-teacher-form',
  templateUrl: './assign-teacher-form.component.html',
  styleUrls: ['./assign-teacher-form.component.css']
})
export class AssignTeacherFormComponent implements OnInit {

  teacherId: number;
  lessonBlockId: number;
  users: User[];

  constructor(private route: ActivatedRoute,
              private lessonService: LessonService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.lessonBlockId = this.route.snapshot.queryParams['lessonBlockId'];
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }

  onChange(newValue: number) {
    this.teacherId = newValue;
  }

  onSubmit() {
    this.lessonService.assignTeacher(this.teacherId, this.lessonBlockId);
  }
}
