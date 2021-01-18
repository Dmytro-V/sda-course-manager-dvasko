import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './users/user-list/user-list.component';
import {UserComponent} from "./users/user/user.component";
import {CoursesListComponent} from "./courses/courses-list/courses-list.component";
import {CourseComponent} from "./courses/course/course.component";
import {AssignTeacherFormComponent} from "./courses/assign-teacher-form/assign-teacher-form.component";

const routes: Routes = [
  { path: 'users', component: UserListComponent },
  { path: 'users/:id', component: UserComponent},
  { path: 'courses', component: CoursesListComponent},
  { path: 'courses/assign-teacher', component: AssignTeacherFormComponent},
  { path: 'courses/:id', component: CourseComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
