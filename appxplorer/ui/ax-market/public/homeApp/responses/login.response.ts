import {ErrorDetails} from './error.details';

export class LoginResponseDto
{
    status: string;
    loginResponse: LoginResponse;
    errorDetails: ErrorDetails;


    constructor(status:string, loginResponse: LoginResponse, errorDetails: ErrorDetails)
    {
        this.loginResponse = loginResponse;
        this.status = status;
        this.errorDetails = errorDetails;
    }
}

export class LoginResponse
{
    token: string;
     
    contructor(token:string, externalUserId: string)
    {
        this.token = token;
    }

}