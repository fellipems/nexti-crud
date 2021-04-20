import { RouterModule, Routes } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ProdutoFormPage } from './produto-form.page';
import { NgModule } from '@angular/core';

const routes: Routes = [
    {
        path: '',
        component: ProdutoFormPage
    }
]

@NgModule({
    declarations: [ProdutoFormPage],
    imports: [
        CommonModule,
        RouterModule.forChild(routes)
    ]
})
export class ProdutoFormModule { }