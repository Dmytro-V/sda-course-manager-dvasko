import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseEnrollmentFormComponent } from './course-enrollment-form.component';

describe('CourseEnrollmentFormComponent', () => {
  let component: CourseEnrollmentFormComponent;
  let fixture: ComponentFixture<CourseEnrollmentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CourseEnrollmentFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseEnrollmentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
