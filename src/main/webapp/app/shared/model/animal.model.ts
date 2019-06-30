export interface IAnimal {
  id?: number;
  name?: string;
  description?: string;
}

export class Animal implements IAnimal {
  constructor(public id?: number, public name?: string, public description?: string) {}
}
