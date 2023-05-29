import { Injectable } from '@angular/core';
import { Cart } from 'src/app/shared/models/Cart';
import { CartItem } from 'src/app/shared/models/CartItem';
import { Item } from 'src/app/shared/models/Item';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cart:Cart = new Cart();

  addToCart(newItem:Item):void {
    let cartItem = this.cart.items.find(item => item.item.id === newItem.id);
    if(cartItem){
      this.changeQuantity(newItem.id, cartItem.quantity + 1);
      return;
    }
    this.cart.items.push(new CartItem(newItem));
  }

  removeFromCart(itemId:number):void{
    this.cart.items = this.cart.items.filter(item => item.item.id != itemId);
  }

  changeQuantity(itemId:number, quantity:number) {
    let cartItem = this.cart.items.find(item => item.item.id == itemId);
    if(!cartItem) return;
    cartItem.quantity = quantity;
  }

  getCart():Cart{
    return this.cart;
  }
}
