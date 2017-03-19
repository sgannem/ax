
export class ViewAccountDetailsResponseDto {
    status: string;
    viewAccountDetailsResponse: ViewAccountDetailsResponse;
    errorDetails: ErrorDetails;

    constructor(status: string, viewAccountDetailsResponse: ViewAccountDetailsResponse, errorDetals: ErrorDetails) {
        this.status = status;
        this.viewAccountDetailsResponse = viewAccountDetailsResponse;
        this.errorDetails = errorDetals;
    }
}

export class ViewAccountDetailsResponse {

    firstName: string;
    lastName: string;
    email: string;
    password: string;
    companyName: string;
    companyAddress: string;
    zipCode: string;
    country: string;
    businessSegment: string;
    companyOrBusinessLogo: any;
    uploadYourTermsAndConditions: any;

    constructor(firstName: string, lastName: string, email: string, password: string, companyName: string, companyAddress: string, zipCode: string, country: string, businessSegment: string, companyOrBusinessLogo: any,
        uploadYourTermsAndConditions: any) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.zipCode = zipCode;
        this.country = country;
        this.businessSegment = businessSegment;
        this.companyOrBusinessLogo = companyOrBusinessLogo;
        this.uploadYourTermsAndConditions = uploadYourTermsAndConditions;
    }
}


export class UpdateAccountDetailsRequestDto {

    updateAccountDetailsRequest: UpdateAccountDetailsRequest;

    constructor(updateAccountDetailsRequest: UpdateAccountDetailsRequest, img: any, tnc: any) {
        this.updateAccountDetailsRequest = updateAccountDetailsRequest;
        var bytesImg = [];
        var bytesTnc = [];
        for (var i = 0; i < img.length; i++) {
            bytesImg.push(img.charCodeAt(i));
        }
        for (var i = 0; i < tnc.length; i++) {
            bytesTnc.push(tnc.charCodeAt(i));
        }
        this.updateAccountDetailsRequest.companyOrBusinessLogo = bytesImg;
        this.updateAccountDetailsRequest.uploadYourTermsAndConditions = bytesTnc;
    }

}

export class UpdateAccountDetailsResponseDto {
    status: string;
    updateAccountDetailsResponse: UpdateAccountDetailsResponse;
    errorDetails: ErrorDetails;

    constructor(status: string, updateAccountDetailsResponse: UpdateAccountDetailsResponse, errorDetals: ErrorDetails) {
        this.status = status;
        this.updateAccountDetailsResponse = updateAccountDetailsResponse;
        this.errorDetails = errorDetals;
    }

}

export class UpdateAccountDetailsRequest {

    firstName: string;
    lastName: string;
    email: string;
    password: string;
    companyName: string;
    companyAddress: string;
    zipCode: string;
    country: string;
    businessSegment: string;
    companyOrBusinessLogo: any =[];
    uploadYourTermsAndConditions: any = [] ;

    constructor(firstName: string, lastName: string, email: string, password: string, companyName: string,
        companyAddress: string, zipCode: string, country: string, businessSegment: string, companyOrBusinessLogo: any,
        uploadYourTermsAndConditions: any) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.zipCode = zipCode;
        this.country = country;
        this.businessSegment = businessSegment;
        this.companyOrBusinessLogo = companyOrBusinessLogo;
        this.uploadYourTermsAndConditions = uploadYourTermsAndConditions;
    }
}

export class UpdateAccountDetailsResponse {

    firstName: string;
    lastName: string;
    email: string;
    password: string;
    companyName: string;
    companyAddress: string;
    zipCode: string;
    country: string;
    businessSegment: string;
    companyOrBusinessLogo: any;
    uploadYourTermsAndConditions: any;

    constructor(firstName: string, lastName: string, email: string, password: string, companyName: string, companyAddress: string, zipCode: string, country: string, businessSegment: string, companyOrBusinessLogo: any,
        uploadYourTermsAndConditions: any) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.zipCode = zipCode;
        this.country = country;
        this.businessSegment = businessSegment;
        this.companyOrBusinessLogo = companyOrBusinessLogo;
        this.uploadYourTermsAndConditions = uploadYourTermsAndConditions;
    }
}

export class BusinessSegmentResponseDto {
    status: string;
    businessSegmentResponse: BusinessSegmentResponse;
    errorDetails: ErrorDetails;

    constructor(status: string, businessSegmentResponse: BusinessSegmentResponse, errorDetals: ErrorDetails) {
        this.status = status;
        this.businessSegmentResponse = businessSegmentResponse;
        this.errorDetails = errorDetals;
    }
}

export class BusinessSegmentResponse {
    businessSegments: BusinessSegment[];

    constructor(businessSegments: BusinessSegment[]) {
        this.businessSegments = businessSegments;
    }

}

export class BusinessSegment {

    id: string;
    name: string;


    constructor(id: string, name: string) {
        this.id = id;
        this.name = name;
    }


}

export class CountryResponseDto {
    status: string;
    countryResponse: CountryResponse;
    errorDetails: ErrorDetails;

    constructor(status: string, countryResponse: CountryResponse, errorDetals: ErrorDetails) {
        this.status = status;
        this.countryResponse = countryResponse;
        this.errorDetails = errorDetals;
    }
}

export class CountryResponse {
    countries: Country[];

    constructor(countries: Country[]) {
        this.countries = countries;
    }

}

export class Country {
    twoCharIsoCountryCode: string;
    threeCharIsoCountryCode: string;
    countryName: string;
    description: string;


    constructor(twoCharIsoCountryCode: string,
        threeCharIsoCountryCode: string, countryName: string, description: string) {
        this.twoCharIsoCountryCode = twoCharIsoCountryCode;
        this.threeCharIsoCountryCode = threeCharIsoCountryCode;
        this.countryName = countryName;
        this.description = description;
    }


}

export class ErrorDetails {
    errorCode: string;
    errorDescription: string;

    constructor(errorCode: string, errorDescription: string) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}

