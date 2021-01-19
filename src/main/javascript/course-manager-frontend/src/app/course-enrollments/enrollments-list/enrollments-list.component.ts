import { Component, OnInit } from '@angular/core';
import {Enrollment} from "../enrollment";
import {EnrollmentsService} from "../enrollments.service";

@Component({
  selector: 'app-enrollments-list',
  templateUrl: './enrollments-list.component.html',
  styleUrls: ['./enrollments-list.component.css']
})
export class EnrollmentsListComponent implements OnInit {

  enrollments: Enrollment[];

  constructor(private enrollmentsService: EnrollmentsService) { }

  ngOnInit(): void {
    this.enrollmentsService.findAll().subscribe(data => {
      this.enrollments = data;
    });
  }

}
