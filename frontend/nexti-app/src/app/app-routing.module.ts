import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/produtos',  // a barra '/' somente no redirect
    pathMatch: 'full'
  },
  {
    path: 'produtos',    // quando carregar o caminho /produtos carregará o module abaixo. Carregamento LAZY. Só carregará quando clicarmos
    loadChildren: () => import('./pages/produtos/produtos.module').then(m => m.ProdutosModule) //'./pages/produtos/produtos.module#ProdutosModule'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
