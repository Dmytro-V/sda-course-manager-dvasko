import {Lesson} from "./lesson";

export class LessonBlock {
  id: number;
  subject: string;
  lessons: Lesson[];
  teacherName: string;
  teacherId: number;
}
