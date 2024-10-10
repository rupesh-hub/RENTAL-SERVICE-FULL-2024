import {inject, Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {catchError, debounce, distinct, distinctUntilChanged, Observable, of, Subject, switchMap, timer} from "rxjs";
import {BaseUser} from "../../shared/model/user.model";
import {State} from "../../shared/model/state.model";
import {SearchQuery} from "./new-conversation/user-search.model";
import {createPaginationOption} from "../../shared/model/request.model";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserSearchService {

  private http: HttpClient = inject(HttpClient);
  searchQuery$ = new Subject<SearchQuery>();
  searchResult$ = new Subject<State<Array<BaseUser>>>();

  searchResult = this.searchResult$.asObservable();

  constructor() {
    this.listenToSearch();
  }

  private listenToSearch(): void {
    this.searchQuery$.pipe(
      distinctUntilChanged(),
      debounce(() => timer(300)),
      switchMap((query) => this.fetchResult(query)
        .pipe(
          catchError((err) => {
            this.searchResult$.next(State.Builder<Array<BaseUser>>()
              .forError(err));
            return of([]);
          })
        ))
    ).subscribe({
      next: users => this.searchResult$.next(State.Builder<Array<BaseUser>>().forSuccess(users)),
      error: (err) => this.searchResult$.next(State.Builder<Array<BaseUser>>().forError(err))
    });
  }

  private fetchResult(searchQuery: SearchQuery): Observable<Array<BaseUser>> {
    let params: HttpParams = createPaginationOption(searchQuery.page);
    params = params.set('query', searchQuery.query)
    return this.http.get<Array<BaseUser>>(`${environment.API_URL}/users/search`, {params});
  }

  search(query: SearchQuery): void {
    this.searchQuery$.next(query);
  }


}
