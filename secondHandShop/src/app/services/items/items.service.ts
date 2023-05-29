import { Injectable } from '@angular/core';
import { Item } from '../../shared/models/Item';

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  constructor() { }

  getItemById(id:number): Item{
    return this.getAll().find(item => item.id == id)!;
  }

  getAll():Item[]{
    return[
      {
        id:1,
        title:'Black Hoodie',
        price:20,
        category:'pullover',
        size:'M',
        imageUrl:'/assets/images/black-sweatshirt.jpg',
      },
      {
        id:2,
        title:'Black Sweatshirt',
        price:25,
        category:'pullover',
        size:'L',
        imageUrl:'/assets/images/black-hoodie.jpg',
      },
      {
        id:3,
        title:'Blue Shirt',
        price:15,
        category:'shirt',
        size:'M',
        imageUrl:'/assets/images/blue-shirt.jpeg',
      },
      {
        id:4,
        title:'Baggy Jeans',
        price:50,
        category:'pants',
        size:'L',
        imageUrl:'/assets/images/jeans.jpg',
      },
    ];
  }
}
