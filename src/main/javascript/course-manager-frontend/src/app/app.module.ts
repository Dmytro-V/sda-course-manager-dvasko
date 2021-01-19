import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list/user-list.component';
import {BrowserModule} from "@angular/platform-browser";
import {UserService} from "./users/user-service.service";
import { UserComponent } from './users/user/user.component';
import { CoursesListComponent } from './courses/courses-list/courses-list.component';
import { CourseComponent } from './courses/course/course.component';
import { AssignTeacherFormComponent } from './courses/assign-teacher-form/assign-teacher-form.component';
import { CourseEnrollmentFormComponent } from './courses/course-enrollment-form/course-enrollment-form.component';
import { EnrollmentsListComponent } from './course-enrollments/enrollments-list/enrollments-list.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserComponent,
    CoursesListComponent,
    CourseComponent,
    AssignTeacherFormComponent,
    CourseEnrollmentFormComponent,
    EnrollmentsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
