import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignTeacherFormComponent } from './assign-teacher-form.component';

describe('AssignTeacherFormComponent', () => {
  let component: AssignTeacherFormComponent;
  let fixture: ComponentFixture<AssignTeacherFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignTeacherFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssignTeacherFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
