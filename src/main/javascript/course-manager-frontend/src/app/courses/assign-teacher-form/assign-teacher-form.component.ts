import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LessonService} from "../../lessons/lesson.service";

@Component({
  selector: 'app-assign-teacher-form',
  templateUrl: './assign-teacher-form.component.html',
  styleUrls: ['./assign-teacher-form.component.css']
})
export class AssignTeacherFormComponent implements OnInit {

  teacherId: number;
  lessonBlockId: number;

  constructor(private route: ActivatedRoute,
              private lessonService: LessonService) { }

  ngOnInit(): void {
    this.lessonBlockId = this.route.snapshot.queryParams['lessonBlockId'];
  }

  onSubmit() {
    this.lessonService.assignTeacher(this.teacherId, this.lessonBlockId);
  }
}
