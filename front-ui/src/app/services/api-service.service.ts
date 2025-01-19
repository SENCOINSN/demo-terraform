import { Injectable } from '@angular/core';

import { GenericService } from './generic-service.service';

@Injectable({
  providedIn: 'root'
})
export class ApiServiceService {
  

  constructor(private genericService:GenericService) { }

  createProduct(data: any) {
    this.genericService.post('/product/create', data);
  }

  allProducts() {
    return this.genericService.get('/product/list');
  }

  getProduct(id: any) {
    return this.genericService.get(`/product/get/${id}`);
  }

  updateProduct(id: any, data: any) {
    return this.genericService.put(`/product/update/${id}`, data);
  }

  deleteProduct(id: any) {
    return this.genericService.delete(`/product/delete/${id}`);
  }
}
