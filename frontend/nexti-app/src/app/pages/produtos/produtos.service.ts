import { Produto } from './produtos.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  private produtos = new BehaviorSubject<Produto[]>([]);  // nosso objeto inicializado vazio
  private baseUrl = 'http://localhost:8080/produtos';

  constructor(private http: HttpClient) { }

  todosProdutos(): Observable<any> {
    return this.http.get<Produto[]>(this.baseUrl)
      .pipe(
        tap(produtos => {
          this.produtos.next(produtos); // quando receber os produtos, damos next no mais recente do nosso observable
        }) // quando der certo a requisição 200 Ok, quero que faça o que está dentro do tap(tap recebe o que o obervable está para enviar(a notificação dos dados) e faz uma operação/manipulação dos dados do observable sem precisar ter dado o subscribe ainda)
    );
  }

  get pProdutos(): Observable<Produto[]> {
    return this.produtos.asObservable();  // vamos transformar nosso BehaviorSubject como um observable, e no nosso component vamos dar um subscribe no produtos e não no todosProdutos
  }
}
