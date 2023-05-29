import { Component, OnInit } from '@angular/core';
import { Item } from '../shared/models/Item';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemsService } from '../services/items/items.service';
import { CartService } from '../services/cart/cart.service';

@Component({
  selector: 'app-item-page',
  templateUrl: './item-page.component.html',
  styleUrls: ['./item-page.component.css']
})
export class ItemPageComponent implements OnInit {
  item!:Item;
  constructor(private activatedRoute:ActivatedRoute,
    private itemsService:ItemsService,
    private cartService:CartService,
    private router:Router){
    activatedRoute.params.subscribe((params) => {
      if(params['id'])
        this.item = itemsService.getItemById(params['id']);
    })

  }
  ngOnInit(): void {
      
  }

  addToCart(){
    this.cartService.addToCart(this.item);
    this,this.router.navigateByUrl('/cart-page');
  }

}
