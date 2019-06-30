import { Moment } from 'moment';

export const enum Gender {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}

export const enum Status {
  NEW = 'NEW',
  ACTIVE = 'ACTIVE',
  SUSPENDED = 'SUSPENDED',
  DEACTIVATED = 'DEACTIVATED'
}

export interface IDriver {
  id?: number;
  name?: string;
  phoneNbr?: string;
  emailAddr?: string;
  gender?: Gender;
  status?: Status;
  createdBy?: number;
  dateCreated?: Moment;
  validatedBy?: number;
  dateValidated?: Moment;
  userLogin?: string;
  userId?: number;
  tourOperatorName?: string;
  tourOperatorId?: number;
}

export class Driver implements IDriver {
  constructor(
    public id?: number,
    public name?: string,
    public phoneNbr?: string,
    public emailAddr?: string,
    public gender?: Gender,
    public status?: Status,
    public createdBy?: number,
    public dateCreated?: Moment,
    public validatedBy?: number,
    public dateValidated?: Moment,
    public userLogin?: string,
    public userId?: number,
    public tourOperatorName?: string,
    public tourOperatorId?: number
  ) {}
}
