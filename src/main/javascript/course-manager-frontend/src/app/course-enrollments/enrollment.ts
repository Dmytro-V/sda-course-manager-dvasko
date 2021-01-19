import {User} from "../users/user";
import {Course} from "../courses/course";

export class Enrollment {
  id: number;
  date: string;
  participant: User;
  course: Course;
}
