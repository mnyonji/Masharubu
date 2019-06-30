export interface ICountry {
  id?: number;
  name?: string;
  alphaCode2?: string;
  alphaCode3?: string;
  countryCode?: string;
  numericCode?: number;
  flagIconContentType?: string;
  flagIcon?: any;
}

export class Country implements ICountry {
  constructor(
    public id?: number,
    public name?: string,
    public alphaCode2?: string,
    public alphaCode3?: string,
    public countryCode?: string,
    public numericCode?: number,
    public flagIconContentType?: string,
    public flagIcon?: any
  ) {}
}
