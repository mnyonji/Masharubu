import { Moment } from 'moment';

export interface ISighting {
  id?: number;
  pictureContentType?: string;
  picture?: any;
  latitude?: number;
  longitude?: number;
  altitude?: number;
  dateSighted?: Moment;
  description?: string;
  driverName?: string;
  driverId?: number;
  animalName?: string;
  animalId?: number;
  parkName?: string;
  parkId?: number;
}

export class Sighting implements ISighting {
  constructor(
    public id?: number,
    public pictureContentType?: string,
    public picture?: any,
    public latitude?: number,
    public longitude?: number,
    public altitude?: number,
    public dateSighted?: Moment,
    public description?: string,
    public driverName?: string,
    public driverId?: number,
    public animalName?: string,
    public animalId?: number,
    public parkName?: string,
    public parkId?: number
  ) {}
}
