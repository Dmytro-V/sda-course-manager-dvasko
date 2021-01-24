import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {
    localStorage.setItem('credentials', btoa('admin:admin'));

  }

  public login(user, pass) {
    localStorage.setItem('credentials', btoa(user + ':' + pass));
  }

  public logout() {
    localStorage.removeItem('credentials');
  }

  public getAuthorization(): string {
    return 'Basic ' + localStorage.getItem('credentials');
  }
}
