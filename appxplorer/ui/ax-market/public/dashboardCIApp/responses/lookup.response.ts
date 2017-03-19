import {ErrorDetails} from './error.details';

export class LookupResponseDto
{
    status: string;
    lookupResponse: Array<LookUp>;
    errorDetails: ErrorDetails;


    constructor(status:string, lookupResponse: Array<LookUp>, 
    errorDetails: ErrorDetails)
    {
        this.status = status;
        this.lookupResponse = lookupResponse;
        this.errorDetails = errorDetails;
    }
}

export class LookUp
{
    id: string
    name:string

    constructor(id: string, name: string)
    {
        this.name = name;
        this.id = id; 
    }
}