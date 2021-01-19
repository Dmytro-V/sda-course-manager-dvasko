import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './users/user-list/user-list.component';
import {UserComponent} from "./users/user/user.component";
import {CoursesListComponent} from "./courses/courses-list/courses-list.component";
import {CourseComponent} from "./courses/course/course.component";
import {AssignTeacherFormComponent} from "./courses/assign-teacher-form/assign-teacher-form.component";
import {CourseEnrollmentFormComponent} from "./courses/course-enrollment-form/course-enrollment-form.component";
import {EnrollmentsListComponent} from "./course-enrollments/enrollments-list/enrollments-list.component";

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'users/:id', component: UserComponent},
  { path: 'courses', component: CoursesListComponent},
  { path: 'courses/assign-teacher', component: AssignTeacherFormComponent},
  { path: 'courses/:id', component: CourseComponent},
  { path: 'enrollments', component: EnrollmentsListComponent},
  { path: 'enrollments/assign-participant', component: CourseEnrollmentFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
