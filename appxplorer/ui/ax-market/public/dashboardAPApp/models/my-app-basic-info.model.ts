export class MyAppBasicInformationModel{
    private providerName: string;
    private emailId: string;
    private phoneNumber: string;
    private bussinessSegment: string;
    private countries:string[];
    private applicationUsed:string;

    public setProviderName(providerName: string)
    {
        this.providerName = providerName;
    }

    public getProviderName()
    {
        return this.providerName;
    }

    public setEmailId(emailId: string)
    {
        this.emailId = emailId;
    }

    public getEmailId()
    {
        return this.emailId;
    }

    public setPhoneNumber(phoneNumber: string)
    {
        this.phoneNumber = phoneNumber;
    }

    public getPhoneNumber()
    {
        return this.phoneNumber;
    }

    public setBussinessSegment(bussinessSegment: string)
    {
        this.bussinessSegment = bussinessSegment;
    }

    public getBussinessSegment()
    {
        return this.bussinessSegment;
    }

    public setCountries(countries: string[])
    {
        this.countries = countries;
    }

    public getCountries()
    {
        return this.countries;
    }

    public setApplicationUsed(applicationUsed: string)
    {
        this.applicationUsed = applicationUsed;
    }

    public getApplicationUsed()
    {
        return this.applicationUsed;
    }

}

export class MyAppMarketingInformationModel{

    private applicationName: string;
    private shortDescription: string;
    private detailedDescription: string;
    private applicationWebsite: string;

    public setApplicationName(applicationName: string)
    {
        this.applicationName = applicationName;
    }

    public getApplicationName()
    {
        return this.applicationName;
    }

    public setShortDescription(shortDescription: string)
    {
        this.shortDescription = shortDescription;
    }

    public getShortDescription()
    {
        return this.shortDescription;
    }

    public setDetailedDescription(detailedDescription: string)
    {
        this.detailedDescription = detailedDescription;
    }

    public getDetailedDescription()
    {
        return this.detailedDescription;
    }

    public setApplicationWebsite(applicationWebsite: string)
    {
        this.applicationWebsite = applicationWebsite;
    }

    public getApplicationWebsite()
    {
        return this.applicationWebsite;
    }

}