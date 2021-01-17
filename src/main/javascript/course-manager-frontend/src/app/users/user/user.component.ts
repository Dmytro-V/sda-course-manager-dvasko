import { Component, OnInit } from '@angular/core';
import {UserService} from "../user-service.service";
import {ActivatedRoute} from "@angular/router";
import {UserDetails} from "../user-details";


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: UserDetails;

  constructor(private userService: UserService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = +this.route.snapshot.params['id'];
    this.userService.findById(id).subscribe(data => {
      this.user = data;
    });
  }

}
