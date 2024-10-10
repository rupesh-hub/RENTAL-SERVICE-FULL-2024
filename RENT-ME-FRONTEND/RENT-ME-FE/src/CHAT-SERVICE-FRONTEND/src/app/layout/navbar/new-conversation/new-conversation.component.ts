import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {UserSearchService} from "../user-search.service";
import {NgbActiveOffcanvas} from "@ng-bootstrap/ng-bootstrap";
import {ToastService} from "../../../shared/toast.service";
import {BaseUser} from "../../../shared/model/user.model";
import {Pagination} from "../../../shared/model/request.model";
import {Subscription} from "rxjs";
import {SearchQuery} from "./user-search.model";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {FormsModule} from "@angular/forms";
import {ConversationSelectorComponent} from "./conversation-selector/conversation-selector.component";

@Component({
  selector: 'wac-new-conversation',
  standalone: true,
  imports: [
    FaIconComponent,
    FormsModule,
    ConversationSelectorComponent
  ],
  templateUrl: './new-conversation.component.html',
  styleUrl: './new-conversation.component.scss'
})
export class NewConversationComponent implements OnInit, OnDestroy {

  searchService: UserSearchService = inject(UserSearchService);
  toastService: ToastService = inject(ToastService);
  activeOffCanvas = inject(NgbActiveOffcanvas);
  public query = "";
  public userResults = new Array<BaseUser>();

  search: Pagination = {
    page: 0,
    size: 20,
    sort: ['firstName', 'ASC']
  }

  loadingSearch: boolean = true;
  searchSubscription: Subscription | undefined;

  ngOnDestroy(): void {
    if (this.searchSubscription)
      this.searchSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.initSearchResultListener();
  }

  initSearchResultListener(): void {
    this.searchSubscription = this.searchService.searchResult
      .subscribe(userState => {
        if (userState.status === 'OK' && userState.value) {
          this.userResults = userState.value;
        } else {
          this.toastService.show("An error occurred while searching for users", "DANGER");
        }
        this.loadingSearch = false;
      });

    const searchQuery:SearchQuery = {
      query: "",
      page: this.search
    }
    this.searchService.search(searchQuery);
  }

  onQueryChange(newQuery: string): void {
    this.loadingSearch = true;
    const searchQuery: SearchQuery = {
      query: newQuery,
      page: this.search
    }
    this.searchService.search(searchQuery);
  }

  onCloseNav() {
    this.activeOffCanvas.close();
  }

  handleConversation(userPublicId: string): void {
    this.activeOffCanvas.close();
  }
}
