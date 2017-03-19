export class SignupRequestDto
{
    signupRequest: LoginRequest;

    constructor(signupRequest: LoginRequest)
    {
        this.signupRequest = signupRequest;
    }

}


export class LoginRequestDto{
    loginRequest:LoginRequest;

    constructor(loginRequest: LoginRequest)
    {
        this.loginRequest = loginRequest;
    }

}

export class LoginRequest {

    userName:string;
    password:string;
    loginType:string;
    accountType:string;


    constructor(userName:string, password:string, loginType:string, accountType:string) {
        this.userName = userName;
        this.password = password;
        this.loginType = loginType;
        this.accountType = accountType;
    }
}