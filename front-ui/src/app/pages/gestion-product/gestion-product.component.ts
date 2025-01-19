import {
  Component,
  OnInit,
} from '@angular/core';

import { ApiServiceService } from '../../services/api-service.service';

@Component({
  selector: 'app-gestion-product',
  standalone: true,
  imports: [],
  templateUrl: './gestion-product.component.html',
  styleUrl: './gestion-product.component.css'
})
export class GestionProductComponent implements OnInit {
  products:any;

  constructor(private apiService:ApiServiceService) { }
  ngOnInit(): void {
    //throw new Error('Method not implemented.');
    this.loadProducts();
  }

  loadProducts(){
    this.apiService.allProducts().subscribe({
      next:resp=>{
        //console.log(resp)
        this.products = resp.data;
      }
    })
  }

    editProduct(id:any){

    }
    deleteProduct(id:any){

    }

}
