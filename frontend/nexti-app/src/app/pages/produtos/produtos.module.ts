import { Routes, RouterModule } from '@angular/router';
import { ProdutosPage } from './produtos.page';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  {
    path: '',
    component: ProdutosPage
  }
]

@NgModule({
  declarations: [ProdutosPage],
  imports: [
    CommonModule,
    RouterModule.forChild(routes) // só abre as páginas conforme vamos precisando
  ]
})
export class ProdutosModule { }
