import { Headers, Http, Response, RequestOptions } from '@angular/http';


let headers = new Headers({ 'Content-Type': 'application/json', 'Authorization': 'bearer ' + sessionStorage.getItem("ciUser")});
let options = new RequestOptions({ headers: headers });

export class HTTPHeader {

    getHeaderOptions()
    {
        return options;
    }

}
