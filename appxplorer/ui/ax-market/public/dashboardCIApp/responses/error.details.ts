export class ErrorDetails{
    errorCode: string;
    errorDescription: string;

    constructor(errorCode: string, errorDescription: string)
    {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;      
    }
}
