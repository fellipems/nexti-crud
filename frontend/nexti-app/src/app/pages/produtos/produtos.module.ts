import { Routes, RouterModule } from '@angular/router';
import { ProdutosPage } from './produtos.page';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

const routes: Routes = [
  {
    path: '',
    component: ProdutosPage
  },
  {
    path: 'novo',
    loadChildren: () => import('./produto-form/produto-form.module').then(m => m.ProdutoFormModule)
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
