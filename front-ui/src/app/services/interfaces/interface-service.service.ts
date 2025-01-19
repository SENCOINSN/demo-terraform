import { Observable } from 'rxjs';

export type paramsType={hideLoader:boolean};

export interface IApiBaseActions{

    Get(url: string, params?: paramsType): Observable<any>;
    GetAll(url: string, params?: paramsType): Observable<any>;
    Post(url: string, params?: paramsType): Observable<any>;
    Put(url: string, params?: paramsType): Observable<any>;
    Delete(url: string, params?: paramsType): Observable<any>;

}