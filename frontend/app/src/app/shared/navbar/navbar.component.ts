import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../auth/services/auth/auth.service';
import { TokenStorageService } from '../../auth/services/token-storage/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent  implements OnInit{

  constructor(private authService:AuthService, private tokenStorageService: TokenStorageService, private router:Router, private activatedRoute:ActivatedRoute) {}
  isAdminRole: boolean = false;

  ngOnInit(): void {
    this.setNavBar();
  }

  setNavBar()
  {
    this.isAdminRole = this.tokenStorageService.hasRoleAdmin();
  }

  public logout():void
  {
      this.authService.logout().subscribe({
          next: () =>
          {
              this.tokenStorageService.cleanToken();
              this.router.navigate(['/..','login']);
          },
          error: error => console.log(error)
      })
  }

}
