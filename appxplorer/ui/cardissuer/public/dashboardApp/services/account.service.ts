import { Component, Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class HttpAccountService {

  //private _accountServiceData:string = "https://httpbin.org/get";
  private _accountServiceData: string = "http://localhost:8080/ax-ciservices/rest/ci/accountdetails";
  constructor(private _http: Http) { }

  // getBasicAccountDetails() {
  //     return this._http.get(this._accountServiceData).map(res => JSON.stringify(res.json())).catch(this.handleError);
  //     /*return this._http.get('https://httpbin.org/get').map((response:Response) => {
  //       console.log(response.json());
  //       response.json();
  //     });*/
  // }


  getBasicAccountDetails() {
    var authHeader = new Headers();
    authHeader.append('Authorization', 'Bearer 6hdeu9boj746aocdbh2v3346pm');
    // var response = this._http.get(this._accountServiceData, {
    //   headers: authHeader
    // }).map(res => JSON.stringify(res.json())).catch(this.handleError);
    var response = this._http.get(this._accountServiceData).map(res => JSON.stringify(res.json())).catch(this.handleError);
    console.log("#Response got from account details service:" + response);
    console.info("#Response got from account details service:" + response);

    console.log("33333333333333333333333333333333333333333333333");

    var json = {
      "viewAccountDetailsResponse": {
        "status": "success",
        "accountDetails": {
          "userName": "gsrini.in@gmail.com"
        },
        "errorDetails": {
          "errorCode": "AX00000",
          "errorDescription": "success"
        }
      }
    }

    var json1 = '{"viewAccountDetailsResponse":{"status":"success","accountDetails":{"userName":"gsrini.in@gmail.com"},"errorDetails":{"errorCode":"AX00000","errorDescription":"success"}}}';

    // var json = {
    //   userDetails: {
    //     "email": "gsrini.in@gmail.com",
    //     "password": "123456"
    //   }
    // };

    var i = new ViewAccountDetailsResponseDto().deserialize(json);
    //  var i = new UserDetails().deserialize(json);
    console.log(i);
    console.log(i.viewAccountDetailsResponse.accountDetails.userName);
    console.log(i.viewAccountDetailsResponse.errorDetails.errorCode);
    console.log(i.viewAccountDetailsResponse.errorDetails.errorDescription);

    var output = JSON.stringify(i);

    console.log("#json values:" + output);

    return response;
  }


  private handleError(error: Response) {
    console.error(error);
    console.info('#error occured during hadling error....');
    return Observable.throw(error.json().error || ' error');
  }
}

interface Serializable<T> {
  deserialize(input: Object): T;
}

class UserDetails implements Serializable<UserDetails> {

  public email: string;
  public password: string;

  deserialize(input) {
    this.email = input.email;
    this.password = input.password;
    return this;
  }
}

class AccountDetails implements Serializable<AccountDetails> {
  public userName: string;

  deserialize(input) {
    this.userName = input.userName;

    return this;
  }
}

class ErrorDetails implements Serializable<ErrorDetails> {

  public errorCode: string;
  public errorDescription: string;

  deserialize(input) {
    this.errorCode = input.errorCode;
    this.errorDescription = input.errorDescription;
    return this;
  }
}



class ViewAccountDetailsResponse implements Serializable<ViewAccountDetailsResponse> {
  status: string;
  accountDetails: AccountDetails;
  errorDetails: ErrorDetails;

  deserialize(input) {
    this.status = input.status;
    this.accountDetails = new AccountDetails().deserialize(input.accountDetails);
    this.errorDetails = new ErrorDetails().deserialize(input.errorDetails);
    return this;
  }

}

class ViewAccountDetailsResponseDto implements Serializable<ViewAccountDetailsResponseDto> {

  viewAccountDetailsResponse: ViewAccountDetailsResponse;

  deserialize(input) {
    this.viewAccountDetailsResponse = input.viewAccountDetailsResponse;
    return this;
  }
}


// {
//     "viewAccountDetailsResponse": {
//         "status": "success",
//         "accountDetails": {
//             "userName": "gsrini.in@gmail.com"
//         },
//         "errorDetails": {
//             "errorCode": "AX00000",
//             "errorDescription": "success"
//         }
//     }
// }

// interface Serializable<T> {
//     deserialize(input: Object): T;
// }



// class Member implements Serializable<Member> {
//     id: number;

//     deserialize(input) {
//         this.id = input.id;
//         return this;
//     }
// }

// class ExampleClass implements Serializable<ExampleClass> {
//     mainId: number;
//     firstMember: Member;
//     secondMember: Member;

//     deserialize(input) {
//         this.mainId = input.mainId;

//         this.firstMember = new Member().deserialize(input.firstMember);
//         this.secondMember = new Member().deserialize(input.secondMember);

//         return this;
//     }
// }

// var json = {
//     mainId: 42,
//     firstMember: {
//         id: 1337
//     },
//     secondMember: {
//         id: -1
//     }
// };

// var instance = new ExampleClass().deserialize(json);
// console.log(instance);