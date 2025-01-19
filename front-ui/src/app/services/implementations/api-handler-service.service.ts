import {
  HttpClient,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';

import {
  Observable,
  tap,
} from 'rxjs';

import {
  IApiBaseActions,
  paramsType,
} from '../interfaces/interface-service.service';

@Injectable({
    providedIn: 'root'
})
export class ApiHandlerService implements IApiBaseActions{
    constructor(public http:HttpClient){
        
    }
    Get(url: string, params?: paramsType): Observable<any> {
        return this.http
        .get(url,{params: this.createParams(params)})
        .pipe(tap((x)=>this.handleResponse(x)))
    }
    GetAll(url: string, params?: paramsType): Observable<any> {
        return this.http
        .get(url, {params: this.createParams(params)})
        .pipe(tap((x) => this.handleResponse(x)));
    }
    Post(url: string, data:any, params?: paramsType): Observable<any> {
        return this.http
      .post(url, data, {params: this.createParams(params)})
      .pipe(tap((x) => this.handleResponse(x)));
    }
    Put(url: string, data:any,params?: paramsType): Observable<any> {
        return this.http
        .put(url, data, {params: this.createParams(params)})
        .pipe(tap((x) => this.handleResponse(x)));
    }
    Delete(url: string, params?: paramsType): Observable<any> {
        return this.http
      .delete(url, {params: this.createParams(params)})
      .pipe(tap((x) => this.handleResponse(x)));
    }

    handleResponse(response: any) { 
        if(response.status == 500){
            alert(response.error.message);
        }
    }

    createParams(params?: paramsType) {
        let httpParams = new HttpParams();
        if(params){
            Object.entries(params).forEach(([key, value]) => {
                httpParams = httpParams.append(key, value);
            })
        }
        return httpParams
    }
}