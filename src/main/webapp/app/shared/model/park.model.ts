export interface IPark {
  id?: number;
  name?: string;
  location?: string;
  countryName?: string;
  countryId?: number;
}

export class Park implements IPark {
  constructor(public id?: number, public name?: string, public location?: string, public countryName?: string, public countryId?: number) {}
}
