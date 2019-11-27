import {Injectable} from '@angular/core';
import {HttpClient, HttpEventType, HttpHeaders, HttpParams} from '@angular/common/http';
import {Post} from './post.model';
import {map, catchError, tap} from 'rxjs/operators';
import {BehaviorSubject, Subject, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  error = new Subject<string>();
  postCreated = new Subject();
  constructor(private http: HttpClient) {

  }
  createAndStorePost(title: string, content: string) {
    const postData: Post = {title, content};
    this.http
      .post(
        'https://making-http-requests.firebaseio.com/posts.json',
        postData)
      .subscribe(responseData => {
        console.log(responseData);
        this.postCreated.next(true);
      },
      error => {
        this.error.next(error.message);
      });
  }

  fetchPost() {
    let searchParams = new HttpParams(); // this will used as query params.
    searchParams = searchParams.append('var2', 'val2').append('custom', 'key');
    return this.http
      .get<{[key: string]: Post}>('https://making-http-requests.firebaseio.com/posts.json',
        {
          headers: new HttpHeaders({'custom-header': 'Hello'}),
          params: searchParams
        })
      .pipe(
        map( responseData => {
          const postsArray: Post[] = [];
          for (const key in responseData) {
            // hasOwnProperty() method is used with for loop as recommended way
            if (responseData.hasOwnProperty(key)) {
              postsArray.push({...responseData[key], id: key});
            }
          }
          return postsArray;
        })/*,
        catchError(errorRes => {
          // send to analytics server
          throwError(errorRes);
        })*/
      );
  }

  deletePosts() {
    return this.http.delete('https://making-http-requests.firebaseio.com/posts.json', { observe: 'events'})
      .pipe(
        tap(event => {
          console.log(event);
          if (event.type === HttpEventType.Response) {
            console.log('type HttpEventType.Response: ' + JSON.stringify(event));
          }
          if (event.type === HttpEventType.Sent) {
            // ...
          }
    })
      );
  }
}
