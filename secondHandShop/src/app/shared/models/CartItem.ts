import { Item } from "./Item";

export class CartItem{
    constructor(item:Item){
        this.item = item;
        this.price;
    }
    item:Item;
    quantity:number = 1;

    get price():number{
        return this.item.price * this.quantity;
    }
}