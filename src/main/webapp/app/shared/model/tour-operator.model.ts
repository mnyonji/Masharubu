import { Moment } from 'moment';

export const enum Status {
  NEW = 'NEW',
  ACTIVE = 'ACTIVE',
  SUSPENDED = 'SUSPENDED',
  DEACTIVATED = 'DEACTIVATED'
}

export interface ITourOperator {
  id?: number;
  name?: string;
  phoneNbr?: string;
  emailAddr?: string;
  status?: Status;
  createdBy?: number;
  dateCreated?: Moment;
  validatedBy?: number;
  dateValidated?: Moment;
  physicalAddress?: string;
  userLogin?: string;
  userId?: number;
}

export class TourOperator implements ITourOperator {
  constructor(
    public id?: number,
    public name?: string,
    public phoneNbr?: string,
    public emailAddr?: string,
    public status?: Status,
    public createdBy?: number,
    public dateCreated?: Moment,
    public validatedBy?: number,
    public dateValidated?: Moment,
    public physicalAddress?: string,
    public userLogin?: string,
    public userId?: number
  ) {}
}
