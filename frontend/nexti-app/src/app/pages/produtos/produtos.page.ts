import { Produto } from './produtos.model';
import { ProdutosService } from './produtos.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-produtos',
  templateUrl: './produtos.page.html',
  styleUrls: ['./produtos.page.css']
})
export class ProdutosPage implements OnInit, OnDestroy {

  produtos: Produto[];
  subs: Subscription;

  constructor(private produtoService: ProdutosService) { }

  ngOnInit() {  // assim que carregar a página
    this.subs = this.produtoService.pProdutos.subscribe(produtos => {
      this.produtos = produtos;
    });   // "escutando" para que, toda vez que tiver um produto novo, ele atualizar a lista
    
    this.produtoService.todosProdutos().subscribe();
  }

  ngOnDestroy() { //Quando a página for "destruida"/fechada pra não ficar pra sempre inscrito
    if(this.subs) {
      this.subs.unsubscribe();
    }
  }
}
