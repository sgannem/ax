import { AppProviderDetail } from './AppProviderDetail';


export class AppDetail {

    constructor(public name: string,public completion: string, public updatedOn: string, public spaceLeftOnCardPercent: string, public tokenKey: string,public appProviderDetail:AppProviderDetail) {

    }
}