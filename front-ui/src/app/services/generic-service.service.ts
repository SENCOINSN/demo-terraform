import { Injectable } from '@angular/core';

import { environment } from '../../environment/environment-prod';
import {
  ApiHandlerService,
} from './implementations/api-handler-service.service';

@Injectable({
    providedIn: 'root'
})
export class GenericService{
    constructor(private apiHandler:ApiHandlerService){}

    apiUrl=environment.api;

    get(endpoint:string){
       return this.apiHandler.Get(`${this.apiUrl}`+endpoint)
    }

    post(endpoint:string,data?:any){
        return this.apiHandler.Post(`${this.apiUrl}`+endpoint,data)
    }

    put(endpoint:string,data?:any){
        return this.apiHandler.Put(`${this.apiUrl}`+endpoint,data)
    }

    delete(endpoint:string){
        return this.apiHandler.Delete(`${this.apiUrl}`+endpoint)
    }



}