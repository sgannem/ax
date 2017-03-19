import {ErrorDetails} from './error.details';

export class SignupResponseDto
{
    status: string;
    signupResponse: SignupResponse;
    errorDetails: ErrorDetails;


    constructor(status:string, signupResponse: SignupResponse, errorDetails: ErrorDetails)
    {
        this.signupResponse = signupResponse;
        this.status = status;
        this.errorDetails = errorDetails;
    }
}

export class SignupResponse
{
    userName: string;
 
    contructor(userName:string)
    {
        this.userName = userName;
    }

}